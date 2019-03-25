package com.pfariasmunoz.droidcafe

import android.content.Context
import android.widget.Toast

fun Context.toast(res: Any, duration: Int = Toast.LENGTH_SHORT) {
    when (res) {
        is Int -> Toast.makeText(this, resources.getString(res).toString(), duration).show()
        is String -> Toast.makeText(this, res, duration).show()
        else -> return
    }
}