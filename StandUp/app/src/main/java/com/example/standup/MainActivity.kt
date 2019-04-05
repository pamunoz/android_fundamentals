package com.example.standup

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private var mNotificationManager: NotificationManager? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val notifyIntent = Intent(this, AlarmReceiver::class.java)
        /**
         * To track the state of the alarm, you need a boolean variable that is true if the alarm exists,
         * and false otherwise. To set this boolean, you can call PendingIntent.getBroadcast() with the
         * FLAG_NO_CREATE flag. If a PendingIntent exists, that PendingIntent is returned;
         * otherwise the call returns null.
         */
        val alarmUp =
            (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null)
        btn_alarm_toggle.isEnabled = alarmUp
        val notifyPendingIntent =
            PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        btn_alarm_toggle.setOnCheckedChangeListener { _, isChecked ->
            val repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES
            val triggerTime = SystemClock.elapsedRealtime() + repeatInterval
            if (isChecked) {
                alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    repeatInterval,
                    notifyPendingIntent
                )
                toast(R.string.alarm_on_message)
            } else {
                //Cancel notification if the alarm is turned off
                alarmManager?.cancel(notifyPendingIntent)
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
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "Notifies every 15 to stand up and walk"
            }
            mNotificationManager?.createNotificationChannel(notificationChannel)
        }
    }

}
