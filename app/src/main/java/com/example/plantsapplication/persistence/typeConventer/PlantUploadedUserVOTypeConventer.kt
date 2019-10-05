package com.example.plantsapplication.persistence.typeConventer

import androidx.room.TypeConverter
import com.example.plantsapplication.data.vos.PlantTypeVO
import com.example.plantsapplication.data.vos.PlantUploadedUserVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PlantUploadedUserVOTypeConventer {
    @TypeConverter
    fun plantUploadedToJson(plantuploadedUserVO: PlantUploadedUserVO): String {
        return Gson().toJson(plantuploadedUserVO)
    }

    @TypeConverter
    fun jsonToPlantUploaded(eventRequirementjson: String): PlantUploadedUserVO {
        return Gson().fromJson<PlantUploadedUserVO>(
            eventRequirementjson,
            PlantUploadedUserVO::class.java!!
        )
    }
}