package com.example.plantsapplication.data.model

import com.example.plantsapplication.data.vos.PlantVO

interface PlantModel {
    fun getAllPlants(onSuccess: (List<PlantVO>) -> Unit, onFailure: (String) -> Unit)
    fun getPlantsById(movietId:String):PlantVO
}