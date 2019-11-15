package com.example.plantsapplication.mvp.presenter

import android.widget.ImageView
import androidx.lifecycle.Observer
import com.example.plantsapplication.BaseActivity
import com.example.plantsapplication.PlantDetailActivity
import com.example.plantsapplication.data.model.PlantModelImpl
import com.example.plantsapplication.delegate.FragmentDelegateOne
import com.example.plantsapplication.mvp.views.PlantlistView
import com.example.plantsapplication.mvp.views.SchoolView
import com.example.plantsapplication.utilites.EM_NULL_EVENT_RESPONSE

class SchoolPresenter:BasePresenter<SchoolView>(),FragmentDelegateOne{
    override fun onClicked(id: String,plantImgView:ImageView) {
        mView.navigateToDetail(id,plantImgView)
    }


    fun onUIReady(activity: BaseActivity){
        val model:PlantModelImpl=PlantModelImpl

        model.getAllPlants { mView.displayError(it) }
            .observe(activity, Observer {
                mView.displayPlantList(it)
            })
    }






}