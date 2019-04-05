package com.example.standup

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    private var mNotificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn_alarm_toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) toast(R.string.alarm_on_message) else toast(R.string.alarm_off_message)
        }
    }
}
