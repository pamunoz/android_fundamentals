package com.example.standup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_alarm_toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) toast(R.string.alarm_on_message) else toast(R.string.alarm_off_message)
        }
    }
}
