package com.example.appwithsettings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.displaySupportFragment(fragment: Fragment) {
    this.supportFragmentManager.beginTransaction()
        .replace(android.R.id.content, fragment)
        .commit()
}