package com.example.standup

import android.content.Context
import android.widget.Toast
import androidx.annotation.IntegerRes

fun Context.toast(message: Any, duration: Int = Toast.LENGTH_SHORT) {
    when(message) {
        is String -> Toast.makeText(this, message, duration).show()
        is @IntegerRes Int -> Toast.makeText(this, this.resources.getString(message), duration).show()
    }
}

val isOreoOrHigher: Boolean
 get() = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O