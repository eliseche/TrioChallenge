package com.eliseche.triochallenge.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliseche.triochallenge.data.ApiManager
import com.eliseche.triochallenge.data.models.Item
import com.eliseche.triochallenge.data.models.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(val apiManager: ApiManager) : ViewModel() {
    private val _uiEvent = MutableStateFlow<RestaurantUiEvent>(RestaurantUiEvent.Menus(emptyList()))
    val uiEvent = _uiEvent

    fun onEvent(event: RestaurantEvent) {
        when (event) {
            is RestaurantEvent.OnMenuClick -> {
                viewModelScope.launch {
                    _uiEvent.value = RestaurantUiEvent.NavigateToRestaurantDetail(event.item)
                }
            }
        }
    }

    fun init() {
        loadMenu()
    }

    private fun loadMenu() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiManager.getRestaurantMenu()
            if (response.isSuccessful) {
                response.body()?.let {
                    _uiEvent.value = RestaurantUiEvent.Menus(it.menus)
                }
            } else {
                _uiEvent.value = RestaurantUiEvent.Error(response.errorBody()?.string() ?: "Error when getting data.")
            }
        }
    }
}



