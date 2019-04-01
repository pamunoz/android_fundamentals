package com.pfariasmunoz.twoactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
        const val EXTRA_MESSAGE: String = "com.pfariasmunoz.twoactivities.extra.MESSAGE"
        const val TEXT_REQUEST: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "-------");
        Log.d(TAG, "onCreate");
        // Restore the state.
        if (savedInstanceState != null) {
            val replyIsVisible = savedInstanceState.getBoolean("reply_visible")
            if (replyIsVisible) {
                tv_text_header_reply.visibility = View.VISIBLE
                tv_reply.apply {
                    text = savedInstanceState.getString("reply_text")
                    visibility = View.VISIBLE
                }

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (tv_text_header_reply.visibility == View.VISIBLE) {
            outState?.apply {
                putBoolean("reply_visible",true)
                putString("reply_text", tv_reply.text.toString())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart");
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause");
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume");
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy");
    }

    fun launchSecondActivity(view: View) {
        val message: String = et_main_reply.text.toString()
        val mainIntent =  Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivityForResult(mainIntent, TEXT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                val reply: String? = data?.getStringExtra(SecondActivity.EXTRA_REPLY)
                tv_text_header_reply.visibility = View.VISIBLE
                tv_reply.apply {
                    text = reply
                    visibility = View.VISIBLE
                }
            }
        }
    }
}
