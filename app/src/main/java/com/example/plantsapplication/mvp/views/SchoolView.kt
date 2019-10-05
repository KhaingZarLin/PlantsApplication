package com.example.plantsapplication.mvp.views

import com.example.plantsapplication.data.vos.PlantVO

interface SchoolView:BaseView{
    fun displayPlantList(eventList:List<PlantVO>)
    fun navigateToDetail(eventId:String)
    fun displayError(errorMessage:String)

}
