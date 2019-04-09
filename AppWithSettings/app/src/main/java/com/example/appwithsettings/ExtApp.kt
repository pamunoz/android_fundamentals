package com.example.appwithsettings

import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat

fun AppCompatActivity.displayPreferenceFragmentCompat(fragment: PreferenceFragmentCompat) {
    this.supportFragmentManager.beginTransaction()
        .replace(android.R.id.content, fragment)
        .commit()
}