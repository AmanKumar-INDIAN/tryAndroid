package com.example.weather.api



sealed class  NetworkResponnce<out T>{
    data class  Success<out T>(val data :T ) :NetworkResponnce<T>()
    data class  Error(val message:String):NetworkResponnce<Nothing>()
    object Loding:NetworkResponnce<Nothing>()
}