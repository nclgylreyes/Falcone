package com.example.falcone

import com.example.falcone.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @GET("planets")
    fun getPlanets(): Call<List<PlanetsItem>>
    @GET("vehicles")
    fun getVehicles(): Call<List<VehicleItem>>
    @Headers(
        "Accept : application/json"
    )
    @POST("token")
    fun postToken(): Call<Token>
    @Headers(
        "Accept : application/json",
        "Content-Type :application/json"
    )
    @POST("find")
    fun postFind(@Body info: Info): Call<ResponseData>
}