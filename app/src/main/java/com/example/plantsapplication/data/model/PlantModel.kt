package com.example.plantsapplication.data.model

import androidx.lifecycle.LiveData
import com.example.plantsapplication.data.vos.PlantVO

interface PlantModel {
    fun getAllPlants(onFailure: (String) -> Unit):LiveData<List<PlantVO>>
    fun getPlantsById(movietId:String):LiveData<PlantVO>
    fun getPlantsByName(plant_name: String): LiveData<List<PlantVO>>
}