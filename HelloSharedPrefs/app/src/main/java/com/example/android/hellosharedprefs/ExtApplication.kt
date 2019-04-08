package com.example.android.hellosharedprefs

import android.content.Context
import android.content.SharedPreferences


private const val sharedPrefFile = "com.example.android.hellosharedprefs"
val Context.sharedPreferences: SharedPreferences
    get() = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
