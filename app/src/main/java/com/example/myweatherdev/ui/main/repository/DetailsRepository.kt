package com.example.myweatherdev.ui.main.repository

import com.example.myweatherdev.ui.main.model.WeatherDTO

interface DetailsRepository {
    fun getWeatherDetailsFromServer(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    )
}