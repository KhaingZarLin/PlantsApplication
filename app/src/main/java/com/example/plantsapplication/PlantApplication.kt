package com.example.plantsapplication

import android.app.Application
import com.example.plantsapplication.data.model.PlantModelImpl

class PlantApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        PlantModelImpl.initDatabase(applicationContext)
    }
}