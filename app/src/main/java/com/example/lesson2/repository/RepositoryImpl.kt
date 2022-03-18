package com.example.lesson2.repository

import com.example.lesson2.domain.Weather
import com.example.lesson2.domain.getRussianCities
import com.example.lesson2.domain.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromRemoteSource() = Weather()
    override fun getWeatherFromLocalSource() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}

//Полный код
//    override fun getWeatherFromRemoteSource(): Weather {
//        return Weather()
//    }
//
//    override fun getWeatherFromLocalSource(): Weather {
//        return Weather()
//    }
//
//    override fun getWeatherFromLocalStorageRus(): List<Weather> {
//        return getRussianCities()
//    }
//
//    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
//        return getWorldCities()
//    }