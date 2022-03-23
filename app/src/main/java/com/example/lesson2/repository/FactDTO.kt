package com.example.lesson2.repository

data class FactDTO(
    //Имена переменных обязательно должны совпадать с именами кода Json, приходящего в ответ на запрос
    val temp: Int,
    val feels_like: Int,
    val condition: String
)