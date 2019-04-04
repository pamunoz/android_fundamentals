package com.example.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private val mCustomReceiver: CustomReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED)
            addAction(Intent.ACTION_POWER_CONNECTED)
        }
        // Register the receiver using the activity context.
        this.registerReceiver(mCustomReceiver, filter)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mCustomReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST))
    }

    override fun onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mCustomReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCustomReceiver)
        super.onDestroy()
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
    }
}
