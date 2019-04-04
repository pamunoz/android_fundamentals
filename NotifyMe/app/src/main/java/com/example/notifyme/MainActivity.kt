package com.example.notifyme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mNotificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_notify.setOnClickListener {
            sendNotification()
        }
        createNotificationChannel()
    }

    private fun sendNotification() {
        val notifyBuilder = getNotificationBuilder()
        mNotificationManager?.notify(NOTIFICATION_ID, notifyBuilder.build())
    }

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
    }

    private fun createNotificationChannel() {
        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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
            mNotificationManager?.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
        .setContentTitle("You've been notified!")
        .setContentText("This is your notification text.")
        .setSmallIcon(R.drawable.ic_android)
}
