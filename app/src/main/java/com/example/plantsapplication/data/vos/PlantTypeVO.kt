package com.example.plantsapplication.data.vos

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class PlantTypeVO(
    @SerializedName("plant_type_name")
    @ColumnInfo(name = "plant_type_name")
    val plantTypeName: String
) {
}