package com.pfariasmunoz.twoactivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    companion object {
        val EXTRA_REPLY = "com.pfariasmunoz.twoactivities.extra.REPLY"
        private val TAG: String = SecondActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tv_message.text = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        Log.d(TAG, "-------")
        Log.d(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy");
    }

    fun returnReply(view: View) {
        val returnIntent = Intent().apply {
            putExtra(EXTRA_REPLY, et_main_reply.text.toString())
        }
        setResult(Activity.RESULT_OK, returnIntent)
        Log.d(TAG, "End Second Activity")
        finish()
    }
}
