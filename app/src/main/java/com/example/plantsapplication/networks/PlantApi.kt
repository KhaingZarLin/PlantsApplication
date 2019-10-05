package com.example.plantsapplication.networks

import com.example.plantsapplication.networks.response.GetPlantsResponse
import com.example.plantsapplication.utilites.GET_PLANTS
import com.example.plantsapplication.utilites.PARAM_ACCESS_TOKEN
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PlantApi {
    @POST(GET_PLANTS)
    fun getAllPlants() : Call<GetPlantsResponse>
}