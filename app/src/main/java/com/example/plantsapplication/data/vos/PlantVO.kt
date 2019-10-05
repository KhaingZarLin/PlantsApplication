package com.example.plantsapplication.data.vos

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "plant_db")
data class PlantVO(

    @SerializedName("plant_id")
    @ColumnInfo(name = "plant_id")
    @PrimaryKey
    var id:String,

    @SerializedName("plant_name")
    @ColumnInfo(name = "plant_name")
    var plantName:String,

   /* @SerializedName("plant_type")
    val plantType: List<PlantTypeVO>,
*/
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description:String,

    @SerializedName("top_tip")
    @ColumnInfo(name = "top_tip")
    var topTip:String,

    @SerializedName("tips")
    @Embedded(prefix = "tips")
    var plantTipsVO: PlantTipsVO,

    @SerializedName("uploaded_user")
    @Embedded(prefix = "uploaded_user")
    var plantUploadedUserVO: PlantUploadedUserVO,

    @SerializedName("plant_photo")
    @ColumnInfo(name = "plant_photo")
    var plantPhoto:String

)
