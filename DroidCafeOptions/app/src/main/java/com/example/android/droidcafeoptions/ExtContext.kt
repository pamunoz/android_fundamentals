package com.example.android.droidcafeoptions

import android.content.Context
import android.widget.Toast

fun Context.toast(message: Any, duration: Int = Toast.LENGTH_SHORT) {
    when(message) {
        is String -> Toast.makeText(this, message, duration).show()
        is Int -> Toast.makeText(this, resources.getString(message), duration).show()
        else -> {}
    }
}