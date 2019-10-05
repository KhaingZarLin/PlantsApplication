package com.example.plantsapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        plantdetailPresenter= PlantDetailPresenter()
        plantdetailPresenter.initPresenter(this)
        plantdetailAdapter= PlantDetailAdapter()

        val plantId=intent.getStringExtra(PlantDetailActivity.EXTRA_PLANT_ID)
        plantdetailPresenter.onUIReady(plantId)

        with(plant_detail_rc)
        {
            setHasFixedSize(true)
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@PlantDetailActivity)
            plant_detail_rc.adapter = plantdetailAdapter
        }
        plantdetailPresenter.onCreate()

    }
    private fun bindData(plantVO:PlantVO)
    {
        tvEventTitle.text=plantVO.plantName
        Glide.with(ivTagImage).load(plantVO.plantUploadedUserVO.userPhoto)
            .into(ivTagImage)
        Glide.with(vpEventDetailImages).load(plantVO.plantPhoto)
            .into(vpEventDetailImages)

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

