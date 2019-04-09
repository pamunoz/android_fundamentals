package com.example.appwithsettings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val KEY_PREF_EXAMPLE_SWITCH = "example_switch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        displayPreferenceFragmentCompat(SettingsFragment())
    }
}
