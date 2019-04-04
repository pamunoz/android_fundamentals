package com.example.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

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
    }

    override fun onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mCustomReceiver)
        super.onDestroy()
    }
}
