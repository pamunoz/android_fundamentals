package com.example.notifyme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        private const val NOTIFICATION_ID = 0
        const val ACTION_UPDATE_NOTIFICATION = "com.example.notifyme.ACTION_UPDATE_NOTIFICATION"
    }

    private var mNotificationManager: NotificationManager? = null
    private val mReceiver: NotificationReceiver = NotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(mReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION))
        btn_notify.setOnClickListener { sendNotification() }
        btn_update.setOnClickListener { updateNotification() }
        btn_cancel.setOnClickListener { cancelNotification() }
        createNotificationChannel()
        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    private fun setNotificationButtonState(isNotifyEnabled: Boolean, isUpdateEnabled: Boolean, isCancelEnabled: Boolean) {
        btn_notify.isEnabled = isNotifyEnabled
        btn_update.isEnabled = isUpdateEnabled
        btn_cancel.isEnabled = isCancelEnabled
    }

    private fun sendNotification() {
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT)
        val notifyBuilder = getNotificationBuilder().addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent)
        mNotificationManager?.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)
    }
    private fun updateNotification() {
        val androidImage = BitmapFactory.decodeResource(resources, R.drawable.mascot_1)
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(NotificationCompat.BigPictureStyle()
            .bigPicture(androidImage)
            .setBigContentTitle("Notification Updated!"))
        mNotificationManager?.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = false, isCancelEnabled = true)
    }
    private fun cancelNotification() {
        mNotificationManager?.cancel(NOTIFICATION_ID)
        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)
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

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivities(this, NOTIFICATION_ID,
            arrayOf(notificationIntent), PendingIntent.FLAG_UPDATE_CURRENT)

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("This is your notification text.")
            .setContentIntent(notificationPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_android)
    }

    inner class NotificationReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            updateNotification()
        }

    }
}
