package com.example.plantsapplication.data.vos

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

class PlantTipsVO (

    @SerializedName("temperature")
    @ColumnInfo(name = "temperature")
    val temperature: String,

    @SerializedName("light")
    @ColumnInfo(name = "light")
    val light: String,

    @SerializedName("placement")
    @ColumnInfo(name = "placement")
    val placement: String
)
{
}