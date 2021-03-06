package com.example.myweatherdev.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myweatherdev.R
import com.example.myweatherdev.ui.main.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}