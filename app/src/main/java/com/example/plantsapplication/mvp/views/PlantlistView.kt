package com.example.plantsapplication.mvp.views

import com.example.plantsapplication.data.vos.PlantVO

interface PlantlistView {
    fun displayPlantList(eventList:List<PlantVO>)
    fun navigateToDetail(eventId:Int)
    fun displayError(errorMessage:String)

}