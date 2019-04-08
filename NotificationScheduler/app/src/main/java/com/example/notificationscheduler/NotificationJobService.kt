package com.example.notificationscheduler

import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context

class NotificationJobService : JobService() {
    
    companion object {
        // Notification channel ID.
        val PRIMARY_CHANNEL_ID ="primary_notification_channel"
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}