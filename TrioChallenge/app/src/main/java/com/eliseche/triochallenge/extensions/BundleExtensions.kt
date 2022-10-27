package com.eliseche.triochallenge.extensions

import android.os.Build
import android.os.Bundle
import java.io.Serializable

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.getCustomSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}