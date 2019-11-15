package com.example.plantsapplication.mvp.views

import android.widget.ImageView
import com.example.plantsapplication.data.vos.PlantVO

interface SchoolView:BaseView{
    fun displayPlantList(eventList:List<PlantVO>)
    fun navigateToDetail(eventId:String,plantImgView:ImageView)
    fun displayError(errorMessage:String)

}
