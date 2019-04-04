package com.example.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null) {
            when(intent.action) {
                Intent.ACTION_POWER_DISCONNECTED -> context.toast( R.string.power_disconnect_message)
                Intent.ACTION_POWER_CONNECTED -> context.toast(R.string.power_connect_message)
                ACTION_CUSTOM_BROADCAST -> context.toast(R.string.custom_broadcast_message)
                else -> context.toast(R.string.unknown_intent_action)
            }
        }
    }
}
