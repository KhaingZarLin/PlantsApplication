package com.example.plantsapplication.mvp.views

import com.example.plantsapplication.data.vos.PlantVO

interface PlantDetailView:BaseView {
    fun displayPlantData(data:PlantVO)
}