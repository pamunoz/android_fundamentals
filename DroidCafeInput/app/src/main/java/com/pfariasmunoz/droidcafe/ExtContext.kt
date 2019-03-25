package com.pfariasmunoz.droidcafe

import android.content.Context
import android.widget.Toast
import androidx.annotation.IntegerRes

fun Context.toast(res: Any, duration: Int = Toast.LENGTH_SHORT) {
    when (res) {
        is @IntegerRes Int -> Toast.makeText(this, resources.getString(res).toString(), duration).show()
        is String -> Toast.makeText(this, res, duration).show()
        else -> return
    }
}