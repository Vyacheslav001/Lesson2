package com.example.lesson2.view

import com.example.lesson2.domain.Weather

interface OnItemViewClickListener {
    fun onItemClick(weather: Weather)
}