package com.example.plantsapplication.networks.datagents

import com.example.plantsapplication.data.vos.PlantVO

interface PlantsDataAgent {
    fun getAllPlants(onSuccess: (List<PlantVO>) -> Unit, onFailure: (String) -> Unit)
}