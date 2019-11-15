package com.example.plantsapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.mvp.presenter.PlantlistPresenter
import com.example.plantsapplication.mvp.views.PlantlistView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity(),PlantlistView {
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
        setUpTransaction()

        mPresenter= PlantlistPresenter(this)

        txt_email_id.text
        ed_password.text


        btn_login.setOnClickListener(View.OnClickListener {
       /*     val intent= Intent(this, SchoolActivity::class.java,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
           startActivity(intent)*/
            startActivity(SchoolActivity.newIntent(this)
                , ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())

        })
    }
    fun setUpTransaction() {
        val displayMatrix = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMatrix)
        val width = displayMatrix.widthPixels

        val loginText = ObjectAnimator.ofFloat(
            txt_login,
            View.ALPHA,
            0f,
            1f
        )
        val profileImage = ObjectAnimator.ofFloat(
            profile_img,
            View.ALPHA,
            0f,
            1f
        )

        val singInText = ObjectAnimator.ofFloat(
            txt_sigin,
            View.ALPHA,
            0f,
            1f
        )
        val textEmail = ObjectAnimator.ofFloat(
            txt_email_id,
            View.ALPHA,
            0f,
            1f
        )
        val editEmail = ObjectAnimator.ofFloat(
            ed_email_id,
            View.ALPHA,
            0f,
            1f
        )
        val textPass = ObjectAnimator.ofFloat(
            txt_pass,
            View.ALPHA,
            0f,
            1f
        )
        val edPass = ObjectAnimator.ofFloat(
            ed_password,
            View.ALPHA,
            0f,
            1f
        )

        val fortget = ObjectAnimator.ofFloat(
            forget_pass,
            View.TRANSLATION_X,
            width.toFloat(),
            0f
        )

        val loginButton = ObjectAnimator.ofFloat(
            btn_login,
            View.TRANSLATION_X,
            (0 - width).toFloat(),
            0f
        )
        val anim1 = AnimatorSet().apply {
            play(loginText).with(profileImage)
                .with(singInText)
        }
        val anim3 =  AnimatorSet().apply {
            play(fortget).with(loginButton)
        }
        val anim2 = AnimatorSet().apply {
            play(textEmail).with(editEmail).with(textPass).with(edPass)
                .before(anim3)
        }

        AnimatorSet().apply {
            play(anim1).before(anim2)
            duration = 1000L
            start()
        }
    }

}
