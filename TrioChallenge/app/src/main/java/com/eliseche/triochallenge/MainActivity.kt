package com.eliseche.triochallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eliseche.triochallenge.ui.restaurant.RestaurantFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        navigateRestaurantFragment()
    }

    private fun navigateRestaurantFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, RestaurantFragment.newInstance())
            .commitNow()
    }
}