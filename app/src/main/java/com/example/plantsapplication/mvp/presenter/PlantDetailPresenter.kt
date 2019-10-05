package com.example.plantsapplication.mvp.presenter

import com.example.plantsapplication.data.model.PlantModelImpl
import com.example.plantsapplication.mvp.views.PlantDetailView

class PlantDetailPresenter:BasePresenter<PlantDetailView>() {
    fun onUIReady(plant_id:String)
    {
        if (plant_id!=null)
        {
            val model=PlantModelImpl
            val plantVO=model.getPlantsById(plant_id)
           mView.displayPlantData(plantVO)
        }
    }

}

