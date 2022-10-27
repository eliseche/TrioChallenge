package com.eliseche.triochallenge.data

import com.eliseche.triochallenge.data.models.RestaurantMenu
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/menu")
    suspend fun getRestaurantMenu(): Response<RestaurantMenu>
}