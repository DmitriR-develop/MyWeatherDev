package com.example.myweatherdev.ui.main.utils

import com.example.myweatherdev.ui.main.model.FactDTO
import com.example.myweatherdev.ui.main.model.Weather
import com.example.myweatherdev.ui.main.model.WeatherDTO
import com.example.myweatherdev.ui.main.model.getDefaultCity

fun convertDtoToModel(weatherDTO: WeatherDTO) : List<Weather> {
    val fact: FactDTO = weatherDTO.fact!!
    return listOf(Weather(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon))
}