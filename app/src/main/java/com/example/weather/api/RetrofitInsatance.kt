package com.example.weather.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInsatance {
  private  const val baseUrl="https://jsonplaceholder.typicode.com"

    private  fun getInstance():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val  watherApii:WatherApi= getInstance().create(WatherApi::class.java)
}