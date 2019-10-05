package com.example.plantsapplication.persistence.typeConventer

import androidx.room.TypeConverter
import com.example.plantsapplication.data.vos.PlantTipsVO
import com.example.plantsapplication.data.vos.PlantUploadedUserVO
import com.google.gson.Gson

object PlantTipsVOConventer {
    @TypeConverter
    fun plantTipToJson(plantuploadedUserVO: PlantTipsVO): String {
        return Gson().toJson(plantuploadedUserVO)
    }

    @TypeConverter
    fun jsonToPlantTips(eventRequirementjson: String): PlantTipsVO {
        return Gson().fromJson<PlantTipsVO>(
            eventRequirementjson,
            PlantUploadedUserVO::class.java!!
        )
    }
}