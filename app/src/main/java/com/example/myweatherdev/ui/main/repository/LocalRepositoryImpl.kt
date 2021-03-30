package com.example.myweatherdev.ui.main.repository

import com.example.myweatherdev.ui.main.model.Weather
import com.example.myweatherdev.ui.main.room.HistoryDao
import com.example.myweatherdev.ui.main.utils.convertHistoryEntityToWeather
import com.example.myweatherdev.ui.main.utils.convertWeatherToEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {

    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(convertWeatherToEntity(weather))
    }
}