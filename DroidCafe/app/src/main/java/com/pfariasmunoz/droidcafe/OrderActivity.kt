package com.pfariasmunoz.droidcafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val order = "Order: ${intent.getStringExtra(MainActivity.EXTRA_MESSAGE)}"
        tv_order.text = order
    }
}
