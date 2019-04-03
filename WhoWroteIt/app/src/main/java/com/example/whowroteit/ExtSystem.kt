package com.example.whowroteit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideTheKeyBoard(context: Context) {
    val inputManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

val Context.isNetworkConnected: Boolean
    get() {
        val manager: ConnectivityManager? = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo: NetworkInfo? = null
        if (manager != null) {
            networkInfo = manager.activeNetworkInfo
        }
        return (networkInfo != null && networkInfo.isConnected)
    }
        