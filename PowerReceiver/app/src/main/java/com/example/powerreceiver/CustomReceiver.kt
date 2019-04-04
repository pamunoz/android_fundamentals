package com.example.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null) {
            when(intent.action) {
                Intent.ACTION_POWER_DISCONNECTED -> context.toast( "Power Disconnected")
                Intent.ACTION_POWER_CONNECTED -> context.toast("Power Connected")
                else -> context.toast("Unknown Intent Action")
            }
        }
    }
}
