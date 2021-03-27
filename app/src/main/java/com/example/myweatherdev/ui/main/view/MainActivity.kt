package com.example.myweatherdev.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myweatherdev.R
import com.example.myweatherdev.databinding.MainActivityBinding
import com.example.myweatherdev.ui.main.view.history.HistoryFragment
import com.example.myweatherdev.ui.main.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, HistoryFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}