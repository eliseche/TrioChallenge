package com.eliseche.triochallenge.data

import com.eliseche.triochallenge.data.models.RestaurantMenu
import retrofit2.Response
import javax.inject.Inject

class ApiManager @Inject constructor(private val apiService: ApiService) : ApiService {
    override suspend fun getRestaurantMenu(): Response<RestaurantMenu> {
        return apiService.getRestaurantMenu()
    }
}