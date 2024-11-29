package com.example.weather


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.ViewModel
import com.example.weather.api.NetworkResponnce

@Composable
fun WeatherApp(modifier: Modifier=Modifier,viewModel: WeatherViewModel){
    var city by remember {
        mutableStateOf("")
    }

    val wetherdata=viewModel.watherResult.observeAsState()
    val keyborcontroler= LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            OutlinedTextField(
                value = city,
                onValueChange ={city=it},
                label = {
                    Text(text = "Search for city")
                },
                modifier = Modifier.weight(1F)
                )
           IconButton(onClick = {
               viewModel.getData(city)
           }) {
               Icon(imageVector = Icons.Default.Search, contentDescription ="Search for city" )

           }

        }

        Button(onClick = { viewModel.getsingleData(1) }) {
            Text(text = "check")
        }


        when (val result=wetherdata.value){
            is NetworkResponnce.Error -> {
                Text(text =result.message )
            }
            NetworkResponnce.Loding ->{
                CircularProgressIndicator()
            }
            is NetworkResponnce.Success -> {
                Text(text = result.data.toString())
            }
            null -> {}
        }
        Button(onClick = {
        keyborcontroler?.hide()
        }) {
            Text(text = "hide key bord")
        }
    }
}