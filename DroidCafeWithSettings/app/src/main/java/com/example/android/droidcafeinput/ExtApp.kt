package com.example.android.droidcafeinput

import android.content.Context
import android.support.annotation.IntegerRes
import android.widget.Toast

fun Context.toast(message: Any, duration: Int = Toast.LENGTH_SHORT) {
    when(message) {
        is String -> Toast.makeText(this, message, duration).show()
        is @IntegerRes Int -> Toast.makeText(this, this.resources.getString(message), duration).show()
        else -> {}
    }
}