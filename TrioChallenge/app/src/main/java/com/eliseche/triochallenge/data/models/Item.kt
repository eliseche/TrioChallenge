package com.eliseche.triochallenge.data.models

data class Item(
    val name: String,
    val description: String,
    val price: Double,
    val url: String
) : java.io.Serializable