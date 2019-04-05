package com.example.standup

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
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
            if (isChecked) {
                deliverNotification(this@MainActivity)
                toast(R.string.alarm_on_message)
            } else {
                //Cancel notification if the alarm is turned off
                mNotificationManager?.cancelAll()
                toast(R.string.alarm_off_message)
            }
        }
        createNotificationChannel()
    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    private fun createNotificationChannel() {
        // Create a notification manager object.
        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID, "Stand Up Notification",
                NotificationManager.IMPORTANCE_HIGH).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "Notifies every 15 to stand up and walk"
            }
            mNotificationManager?.createNotificationChannel(notificationChannel)
        }
    }

    private fun deliverNotification(context: Context) {
        val contentIntent = Intent(context, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stand_up)
            .setContentTitle("Stand Up Alert")
            .setContentText("You should stand up and walk around now!")
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        mNotificationManager?.notify(NOTIFICATION_ID, builder.build())
    }
}
