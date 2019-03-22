package com.pfariasmunoz.hellotoast

import android.content.Context
import android.widget.Toast

fun Context.toast(res: Any, duration: Int = Toast.LENGTH_SHORT) {
    val message: String = when (res) {
        is String -> res
        is Int -> this.resources.getString(res)
        else -> return
    }
    Toast.makeText(this, message, duration).show()
}