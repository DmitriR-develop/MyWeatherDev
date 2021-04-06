package com.example.myweatherdev.ui.main.app

import com.example.myweatherdev.ui.main.model.Weather

sealed class AppState {
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}