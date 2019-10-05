package com.example.plantsapplication.persistence.typeConventer

import androidx.room.TypeConverter
import com.example.plantsapplication.data.vos.PlantTypeVO
import com.example.plantsapplication.data.vos.PlantVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlantTypeVOConventer {

    @TypeConverter
    fun toString(list: List<PlantTypeVO>): String{
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<PlantTypeVO>{
        val typeToken = object: TypeToken<List<PlantTypeVO>>(){}.type
        return Gson().fromJson(json, typeToken)
    }

}
