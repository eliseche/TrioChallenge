package com.eliseche.triochallenge.ui.restaurant

import com.eliseche.triochallenge.data.models.Item
import com.eliseche.triochallenge.data.models.Menu

sealed class RestaurantEvent {
    data class OnMenuClick(val item: Item) : RestaurantEvent()
}

sealed class RestaurantUiEvent {
    data class Menus(val menu: List<Menu>) : RestaurantUiEvent()
    data class Error(val message: String) : RestaurantUiEvent()
    data class NavigateToRestaurantDetail(val item: Item) : RestaurantUiEvent()
}