package com.example.myweatherdev.ui.main.viewmodel

import com.example.myweatherdev.ui.main.model.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}