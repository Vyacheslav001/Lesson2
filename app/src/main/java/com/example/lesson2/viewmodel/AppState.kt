package com.example.lesson2.viewmodel

import com.example.lesson2.domain.Weather

sealed class AppState {
    object Loading : AppState() //FIXME object //Loading - это состояние, он может быть только один, поэтому он object
//    data class Success(val weatherData: Weather) : AppState() //Редактируем эту строку
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
}

class AppState2: AppState(){

}