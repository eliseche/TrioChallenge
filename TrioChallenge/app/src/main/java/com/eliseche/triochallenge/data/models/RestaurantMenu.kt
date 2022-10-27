package com.eliseche.triochallenge.data.models

import com.eliseche.triochallenge.data.models.Menu

data class RestaurantMenu(
    val name: String,
    val description: String,
    val menus: List<Menu>
)