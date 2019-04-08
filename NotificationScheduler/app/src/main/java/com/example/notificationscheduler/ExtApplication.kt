package com.example.notificationscheduler

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.getSystemService

val Context.notificationManager: NotificationManager
    get() = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager