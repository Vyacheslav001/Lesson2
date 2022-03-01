package com.example.lesson2.repository

import com.example.lesson2.domain.Weather

class RepositoryImpl: Repository {
    override fun getWeatherFromRemoteSource(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalSource(): Weather {
        return Weather()
    }
}