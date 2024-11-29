package com.example.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.api.NetworkResponnce
import com.example.weather.api.RetrofitInsatance
import com.example.weather.api.Wathermodel
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {
    private  val WatherApi=RetrofitInsatance.watherApii
    private val  _wetherResult=MutableLiveData<NetworkResponnce<Wathermodel>>()
      val  watherResult:LiveData<NetworkResponnce<Wathermodel>> = _wetherResult


    fun getData(city:String){
     Log.d("check",city)
        _wetherResult.value=NetworkResponnce.Loding
        viewModelScope.launch {
         val responv=   WatherApi.getTdos()

            try {
                if(responv.isSuccessful){
                    Log.d("check",responv.body().toString())
                    responv.body()?.let {
                        _wetherResult.value=NetworkResponnce.Success(it)
                    }
                }else{
                    _wetherResult.value=NetworkResponnce.Error("faild to get values")
                }
            }catch (E:Exception){
    _wetherResult.value=NetworkResponnce.Error("cant able to get data server")
            }

        }
    }
    fun getsingleData(id:Int){
        Log.d("Id is",id.toString())
        viewModelScope.launch {
            val responv=   WatherApi.getTodo(id)
            if(responv.isSuccessful){
                Log.d("check",responv.body().toString())

            }else{
                Log.d("check",responv.message())

            }
        }
    }
}