package com.example.plantsapplication.data.model

import android.content.Context
import com.example.plantsapplication.networks.datagents.PlantsDataAgent
import com.example.plantsapplication.networks.datagents.RetroFitAgent
import com.example.plantsapplication.persistence.PlantsDB

abstract class BaseModel {
    protected val dataAgent: PlantsDataAgent=RetroFitAgent


    protected lateinit var database: PlantsDB

    fun initDatabase(context: Context){
        database = PlantsDB.getInstance(context)
    }

}
