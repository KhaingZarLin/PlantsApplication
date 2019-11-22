package com.example.plantsapplication.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.plantsapplication.R
import com.example.plantsapplication.adapter.SchoolAdapter
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.mvp.presenter.SchoolPresenter
import com.example.plantsapplication.mvp.views.SchoolView

import kotlinx.android.synthetic.main.activity_school.*

class SchoolActivity : BaseActivity(), SchoolView{

    companion object {
        fun newIntent(context: MainActivity): Intent {
            return Intent(context, SchoolActivity::class.java)
        }
    }

    override fun displayPlantList(eventList: List<PlantVO>) {
        schoolAdapter.setNewData(eventList as MutableList<PlantVO>)
    }

    override fun navigateToDetail(id: String,plantImgView:ImageView) {
       val pair= Pair.create(plantImgView as View,"tplantImage")
       val options= ActivityOptionsCompat.makeSceneTransitionAnimation(this,pair)
        startActivity(
            PlantDetailActivity.newIntent(
                applicationContext,
                id
            ),options.toBundle())
    }


    override fun displayError(errorMessage: String) {
         Snackbar.make(rootview, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private lateinit var schoolPresenter: SchoolPresenter

    private lateinit var schoolAdapter: SchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAnimations()
        setContentView(R.layout.activity_school)

        schoolPresenter= SchoolPresenter()
        schoolPresenter.initPresenter(this)
        schoolAdapter = SchoolAdapter(schoolPresenter)

        with(rc_school)
        {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@SchoolActivity)
            rc_school.adapter = schoolAdapter
        }

        ed_search.setOnEditorActionListener(TextView.OnEditorActionListener { vs, actionId, events ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchName = ed_search.text.toString()
                return@OnEditorActionListener true
            }
            false
        })

        ed_search.setOnKeyListener { vs, keyCode, events ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                schoolPresenter.onUIReady(this)
            }
            false
        }

        ed_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                searchByKeyword(s.toString())
            }
        })

        schoolPresenter.onUIReady(this)

    }

    private fun setUpAnimations(){
        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            val fade = android.transition.Fade()
            fade.interpolator = AccelerateDecelerateInterpolator()
            fade.duration = 1500

            enterTransition = fade
           exitTransition = fade
        }
    }

    fun searchByKeyword(keyword: String){
        schoolAdapter.setNewData(model.getPlantsByName(keyword) as MutableList<PlantVO>)
        rc_school.setAdapter(schoolAdapter)
    }

    override fun onStart() {
        super.onStart()
      //  mPresenter.onStart()
    }

    override fun onResume() {
        super.onResume()
      //  mPresenter.onResume()
    }

    override fun onStop() {
        super.onStop()
      //  mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
      //  mPresenter.onDestory()
    }

    override fun onPause() {
        super.onPause()
     //   mPresenter.onPause()
    }



//    override fun onClicked(id: String) {
//        val intent=this?.let { PlantDetailActivity.newIntent(it,id)  }
//        startActivity(intent)
//    }

}
