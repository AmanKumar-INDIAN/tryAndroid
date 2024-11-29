package com.example.weather.api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WatherApi {
    @GET("/todos")
    suspend fun getTdos(

    ) : Response<Wathermodel>

    @GET("/todos/{id}")
    suspend fun getTodo(
        @Path("id") id:Int
    ) : Response<WAthermodelItem>
}