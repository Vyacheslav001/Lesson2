package com.example.lesson2.viewmodel

import com.example.lesson2.domain.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    //Loading - это состояние, он может быть только один, поэтому он object
    object Loading : AppState() //FIXME object
}
