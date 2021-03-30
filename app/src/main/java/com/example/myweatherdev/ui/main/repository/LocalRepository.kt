package com.example.myweatherdev.ui.main.repository

import com.example.myweatherdev.ui.main.model.Weather

interface LocalRepository {
    fun getAllHistory() : List<Weather>
    fun saveEntity(weather: Weather)
}