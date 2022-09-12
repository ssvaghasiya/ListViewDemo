package com.task.artivatic.utils

import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.*


object Utils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }
}