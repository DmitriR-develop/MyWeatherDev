package com.example.myweatherdev.ui.main.repository

import com.example.myweatherdev.ui.main.model.Weather
import com.example.myweatherdev.ui.main.model.getRussianCities
import com.example.myweatherdev.ui.main.model.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}