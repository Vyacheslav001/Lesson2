package com.example.lesson2.repository

import com.example.lesson2.domain.Weather

interface Repository {
    fun getWeatherFromRemoteSource(): Weather
    fun getWeatherFromLocalSource(): Weather
}