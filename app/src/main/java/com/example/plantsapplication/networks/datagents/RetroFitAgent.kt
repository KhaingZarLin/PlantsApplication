package com.example.plantsapplication.networks.datagents

import com.example.plantsapplication.data.vos.PlantVO
import com.example.plantsapplication.networks.PlantApi
import com.example.plantsapplication.networks.response.GetPlantsResponse
import com.example.plantsapplication.utilites.BASE_URL
import com.example.plantsapplication.utilites.EM_NULL_EVENT_RESPONSE
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitAgent: PlantsDataAgent{

    private val eventApi: PlantApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        eventApi = retrofit.create(PlantApi::class.java)

    }
    override fun getAllPlants(
        onSuccess: (List<PlantVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call = eventApi.getAllPlants()
        call.enqueue(object  : Callback<GetPlantsResponse> {
            override fun onFailure(call: Call<GetPlantsResponse>, t: Throwable) {
                onFailure(t.localizedMessage)
            }

            override fun onResponse(
                call: Call<GetPlantsResponse>,
                response: Response<GetPlantsResponse>
            ) {
                val eventsResponse = response.body()
                if (eventsResponse != null){
                    if(eventsResponse.data != null){
                        onSuccess(eventsResponse.data)
                    }else{
                        onFailure(eventsResponse.message)
                    }
                }else{
                    onFailure(EM_NULL_EVENT_RESPONSE)
                }
            }
        })
    }




}