package com.pfariasmunoz.implicitintents

import android.app.Activity
import android.content.Intent
import android.util.Log

fun Activity.startImplicitIntent(intent: Intent) {
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        Log.d("ImplicitIntents", "Can't handle this!");
    }
}