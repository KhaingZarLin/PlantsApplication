package com.example.plantsapplication.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.plantsapplication.R
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.mvp.presenter.PlantlistPresenter
import com.example.plantsapplication.mvp.views.PlantlistView
import com.example.plantsapplication.utilites.RC_SIGN_IN
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.functions.BiFunction
import org.json.JSONObject
import java.util.*

class MainActivity : BaseActivity(),PlantlistView, OnCompleteListener<AuthResult> {

    lateinit var callbackManager : CallbackManager
    lateinit var firebaseauth : FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    lateinit var progressDialog : ProgressDialog

    override fun displayPlantList(eventList: List<PlantVO>) {

    }

    override fun navigateToDetail(eventId: Int) {

    }

    override fun displayError(errorMessage: String) {

    }

    private lateinit var mPresenter:PlantlistPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseauth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()

        //FacebookSdk.sdkInitialize(applicationContext);

        val userName = Observable.create<String> { emitter ->
            ed_username.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    emitter.onNext(p0.toString())
                }

            })
        }

        val password = Observable.create<String> { emitter ->
            ed_password.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    emitter.onNext(p0.toString())
                }

            })
        }


        Observable.combineLatest<String,String,Boolean>(
            userName,
            password,
            BiFunction { name, password ->
                name.length > 6 && password.length > 6 && name.contains("@")
            }
        ).subscribe{
            if(it==true) {
                btn_login.isEnabled = true
                btn_login.setTextColor(resources.getColor(R.color.green))
            }
            else {
                btn_login.isEnabled = false
                btn_login.setTextColor(resources.getColor(R.color.gray))
            }
        }

        mPresenter= PlantlistPresenter(this)

        btn_login.setOnClickListener{
            showLoading()
            signInWithEmailPassword(ed_username.text.toString(),ed_password.text.toString())
        }

        btn_fb.setOnClickListener {
            showLoading()
            signInWithFacebook()
        }

        btn_google.setOnClickListener {
            showLoading()
            signInWithGoogle()
        }

    }

    fun showLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        if (!progressDialog.isShowing() && !isFinishing) {
            progressDialog.show()
        }
    }

    fun hideLoading() {
        if (progressDialog!=null && progressDialog.isShowing()) {
            progressDialog.dismiss()
        }
    }

    //Facebook sign in
    private fun signInWithFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this,
            Arrays.asList( "public_profile","name","email"))
        LoginManager.getInstance().registerCallback(callbackManager,object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                if(result!=null){
                    GraphRequest.newMeRequest(result.accessToken,object : GraphRequest.GraphJSONObjectCallback{
                        override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {

                            hideLoading()
                            startActivity(
                                SchoolActivity.newIntent(this@MainActivity))
                        }

                    })
                }
                else Log.d("test---","result null")

            }

            override fun onCancel() {
                hideLoading()
                Log.d("test---","cancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d("test---","error")
            }

        })
    }

    //Google sign in
    private fun signInWithGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // Email pass sign in
    private fun signInWithEmailPassword(email : String, password : String){
        firebaseauth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this)
    }

    // For email login
    override fun onComplete(p0: Task<AuthResult>) {
        hideLoading()
        startActivity(
            SchoolActivity.newIntent(this)
            , ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())

    }


    //For google authentication
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data) as Task
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                firebaseauth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = firebaseauth.currentUser
                            hideLoading()
                            startActivity(
                                SchoolActivity.newIntent(this)
                                , ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
                        } else {
                            Log.w("test---", "signInWithCredential:failure", task.exception)
                        }

                        // ...
                    }
            } catch (e: ApiException) {
                Log.w("test---", "Google sign in failed",e)
            }
        }

    }

}
