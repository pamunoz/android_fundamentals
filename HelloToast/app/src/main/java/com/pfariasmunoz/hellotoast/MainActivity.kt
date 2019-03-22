package com.pfariasmunoz.hellotoast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showToast(view: View) = toast(R.string.toast_message)

    fun countUp(view: View) {
        ++mCount
        if (tv_show_count != null) tv_show_count.text = mCount.toString()
    }
}
