package com.example.plantsapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantsapplication.adapter.PlantDetailAdapter
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.delegate.FragmentDelegateOne
import com.example.plantsapplication.mvp.presenter.PlantDetailPresenter
import com.example.plantsapplication.mvp.views.PlantDetailView

import kotlinx.android.synthetic.main.activity_plant_detail.*
import kotlinx.android.synthetic.main.activity_school.*
import kotlinx.android.synthetic.main.flower_item_view.view.*

class PlantDetailActivity : BaseActivity() ,PlantDetailView{
    override fun displayPlantData(data: PlantVO) {
        bindData(data)
    }

    private lateinit var plantdetailAdapter: PlantDetailAdapter
    private lateinit var plantdetailPresenter:PlantDetailPresenter

    private var isfav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAnimations()
        setContentView(R.layout.activity_plant_detail)
        setUpListener()

        plantdetailPresenter= PlantDetailPresenter()
        plantdetailPresenter.initPresenter(this)
        plantdetailAdapter= PlantDetailAdapter()

        val plantId=intent.getStringExtra(PlantDetailActivity.EXTRA_PLANT_ID)
        plantdetailPresenter.onUIReady(plantId)

//        with(plant_detail_rc)
//        {
//            setHasFixedSize(true)
//            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@PlantDetailActivity)
//            plant_detail_rc.adapter = plantdetailAdapter
//        }
      //  plantdetailPresenter.onCreate()
        back_iv.setOnClickListener {
            finish()
        }

        plantdetailPresenter.onUIReady(plantId)

    }
    private fun setUpAnimations(){
        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            val slide = Slide()
            slide.slideEdge = Gravity.RIGHT
            slide.interpolator = AccelerateDecelerateInterpolator()
            slide.duration = 100

            enterTransition = slide
            exitTransition = slide
        }
    }
    private fun setUpListener()
    {
       btn_fav.setOnClickListener {
           if (!isfav) {
               btn_fav.speed = 1.0f
               btn_fav.playAnimation()
               isfav = true
           } else {
               btn_fav.speed = -4.0f
               btn_fav.playAnimation()
               isfav = false
           }
       }
    }
    private fun bindData(plantVO:PlantVO)
    {
        tvEventTitle.text=plantVO.plantName
        Glide.with(ivTagImage).load(plantVO.plantUploadedUserVO.userPhoto)
            .into(ivTagImage)
        Glide.with(vpEventDetailImages).load(plantVO.plantPhoto)
            .into(vpEventDetailImages)
        tvDescription.text=plantVO.description
        textView.text=plantVO.plantTipsVO.temperature
        textView1.text=plantVO.plantTipsVO.light
        textView2.text=plantVO.plantTipsVO.placement
        tvname.text="by "+plantVO.plantUploadedUserVO.name

    }

    companion object{
        const val EXTRA_PLANT_ID="eventIdExtra"

        fun newIntent(context: Context, plantId:String): Intent {

            return Intent(context,PlantDetailActivity::class.java).apply {
                putExtra(EXTRA_PLANT_ID,plantId)
            }

        }
    }

}

