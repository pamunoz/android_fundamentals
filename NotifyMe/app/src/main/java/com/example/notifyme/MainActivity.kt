package com.example.notifyme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mNotificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_notify.setOnClickListener {
            sendNotification()
        }
    }

    fun sendNotification() {

    }

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
    }

    fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create a Notification Channel
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID, "Mascot Notification",
                NotificationManager.IMPORTANCE_HIGH).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "Notification from Mascot"
            }
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
