package com.example.plantsapplication.mvp.presenter

import com.example.plantsapplication.PlantDetailActivity
import com.example.plantsapplication.data.model.PlantModelImpl
import com.example.plantsapplication.delegate.FragmentDelegateOne
import com.example.plantsapplication.mvp.views.PlantlistView
import com.example.plantsapplication.mvp.views.SchoolView
import com.example.plantsapplication.utilites.EM_NULL_EVENT_RESPONSE

class SchoolPresenter:BasePresenter<SchoolView>(),FragmentDelegateOne{
    override fun onClicked(id: String) {
        mView.navigateToDetail(id)
    }

    override fun onCreate(){

        val model:PlantModelImpl=PlantModelImpl
        model.getAllPlants(
            onSuccess = {plantVo ->
                mView.displayPlantList(plantVo.toMutableList())
            },
            onFailure = {
                mView.displayError(EM_NULL_EVENT_RESPONSE)
            }
        )
    }

    override  fun onStart(){}
    override fun onResume(){}
    override  fun onPause(){}
    override  fun onStop(){}
    override fun onDestory(){}

}