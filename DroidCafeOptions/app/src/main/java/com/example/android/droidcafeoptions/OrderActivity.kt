/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.droidcafeoptions

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

/**
 * This activity handles radio buttons for choosing a delivery method for an
 * order, and EditText input controls.
 */
class OrderActivity : AppCompatActivity() {

    /**
     * Sets the content view to activity_order, and gets the intent and its
     * data.
     *
     * @param savedInstanceState Saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        // Get the intent and its data.
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.order_textview)
        textView.text = message
    }

    /**
     * Checks which radio button was clicked and displays a toast message to
     * show the choice.
     *
     * @param view The radio button view.
     */
    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked
        // Check which radio button was clicked.
        when (view.getId()) {
            R.id.sameday -> if (checked)
            // Same day service
                displayToast(getString(R.string.same_day_messenger_service))
            R.id.nextday -> if (checked)
            // Next day delivery
                displayToast(getString(R.string.next_day_ground_delivery))
            R.id.pickup -> if (checked)
            // Pick up
                displayToast(getString(R.string.pick_up))
            else -> {
            }
        }// Do nothing.
    }

    /**
     * Displays the actual message in a toast message.
     *
     * @param message Message to display.
     */
    fun displayToast(message: String) {
        Toast.makeText(applicationContext, message,
                Toast.LENGTH_SHORT).show()
    }
}
