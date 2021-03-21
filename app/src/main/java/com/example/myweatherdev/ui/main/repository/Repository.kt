package com.example.myweatherdev.ui.main.repository

import com.example.myweatherdev.ui.main.model.Weather

interface Repository {
    fun getWeatherFromServer() : Weather
    fun getWeatherFromLocalStorageRus() : List<Weather>
    fun getWeatherFromLocalStorageWorld() : List<Weather>
}