package com.example.plantsapplication.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.utilites.DUMMY_ACCESS_TOKEN
import java.util.ArrayList

object PlantModelImpl: BaseModel(), PlantModel {

    override fun getPlantsById(movietId: String): LiveData<PlantVO> {
        /*val plantVo =database.plantDao().getPlanstsById(movietId)
        return plantVo*/
        return  Transformations.distinctUntilChanged(
            database.plantDao().getPlanstsById(movietId)
        )
    }
//    override fun getAllPlants(onSuccess: (List<PlantVO>) -> Unit, onFailure: (String) -> Unit) {
//        val movieDataFromDB = database.plantDao().getPlants()
//        if (movieDataFromDB.isNotEmpty()) {
//            onSuccess(movieDataFromDB)
//        }else {
//            dataAgent.getAllPlants(
//                onSuccess={
//                    database.plantDao().insertPlants(it)
//                    onSuccess(it)
//                },
//                onFailure= {
//                    Log.d("Hello","Error Message");
//                })
//        }
//    }

    override fun getAllPlants(onFailure: (String) -> Unit):LiveData<List<PlantVO>>
    {
        return Transformations.distinctUntilChanged(database.plantDao().getPlants())

    }
    override fun getPlantsByName(plant_name: String): LiveData<List<PlantVO>>
    {

        val plantVO =Transformations.distinctUntilChanged(database.plantDao().getPlants())

        return plantVO
    }


}