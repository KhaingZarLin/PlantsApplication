package com.example.plantsapplication.networks.response

import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.utilites.CODE_RESPONSE_OK
import com.google.gson.annotations.SerializedName

data class GetPlantsResponse(

    @SerializedName("code")
    val code: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<PlantVO>?
) {
    fun isResponseOK(): Boolean {
        return code == CODE_RESPONSE_OK && data != null
    }
}