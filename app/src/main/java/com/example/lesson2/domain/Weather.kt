package com.example.lesson2.domain

data class Weather(val city: City = getDefaultCity(),
                   val temperature: Int = -1,
                   val feelsLike: Int = -5)

private fun getDefaultCity() = City("Moscow", 55.0, 37.0)
