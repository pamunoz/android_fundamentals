package com.example.simpleasynctask

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TEXT_STATE = "currentText"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTask(view: View) {
        // Put a message in the text view
        tv_task.text = getString(R.string.sleep_message)
        // Start the AsyncTask.
        SimpleAsynctask(tv_task).execute()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save the state of the textView
        outState.putString(TEXT_STATE, tv_task.text.toString())
    }
}
