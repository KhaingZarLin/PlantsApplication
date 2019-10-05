package com.example.plantsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plantsapplication.data.model.PlantModel
import com.example.plantsapplication.data.model.PlantModelImpl

abstract class BaseActivity: AppCompatActivity() {
    protected lateinit var model: PlantModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = PlantModelImpl
    }
}