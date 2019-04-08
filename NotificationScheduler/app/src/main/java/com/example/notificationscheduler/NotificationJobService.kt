package com.example.notificationscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat

class NotificationJobService : JobService() {

    companion object {
        // Notification channel ID.
        const val PRIMARY_CHANNEL_ID ="primary_notification_channel"
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        // Creates the notification channel
        createNotificationChannel()

        // Set up the notification content intent to launch the app when clicked
        val contentPendingIntent = PendingIntent.getActivity(this, 0,
            Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID).apply {
            setContentTitle("Job Service")
            setContentText("Your job ran to completion!")
            setContentIntent(contentPendingIntent)
            setSmallIcon(R.drawable.ic_job_running)
            priority = NotificationCompat.PRIORITY_HIGH
            setDefaults(NotificationCompat.DEFAULT_ALL)
            setAutoCancel(true)
        }
        notificationManager.notify(0, builder.build())
        return false
    }

    private fun createNotificationChannel() {
        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID, "Job service notification", NotificationManager.IMPORTANCE_HIGH).apply {
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
                description = "Notification from Job service"
            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

}