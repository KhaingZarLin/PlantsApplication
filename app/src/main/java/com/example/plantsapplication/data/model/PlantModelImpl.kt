package com.example.plantsapplication.data.model

import android.util.Log
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.utilites.DUMMY_ACCESS_TOKEN

object PlantModelImpl: BaseModel(), PlantModel {

    override fun getPlantsById(movietId: String): PlantVO {
        val plantVo =database.plantDao().getPlanstsById(movietId)
        return plantVo
    }
    override fun getAllPlants(onSuccess: (List<PlantVO>) -> Unit, onFailure: (String) -> Unit) {
        val movieDataFromDB = database.plantDao().getPlants()
        if (movieDataFromDB.isNotEmpty()) {
            onSuccess(movieDataFromDB)
        }else {
            dataAgent.getAllPlants(
                onSuccess={
                    database.plantDao().insertPlants(it)
                    onSuccess(it)
                },
                onFailure= {
                    Log.d("Hello","Error Message");
                })
        }
    }

}