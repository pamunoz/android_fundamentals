package com.example.appwithsettings

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

fun AppCompatActivity.displayPreferenceFragmentCompat(fragment: PreferenceFragmentCompat) {
    this.supportFragmentManager.beginTransaction()
        .replace(android.R.id.content, fragment)
        .commit()
}

val Context.defualtSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)