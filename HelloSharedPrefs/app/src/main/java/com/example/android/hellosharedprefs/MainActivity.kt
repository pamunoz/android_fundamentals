/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.hellosharedprefs

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

/**
 * HelloSharedPrefs is an adaptation of the HelloToast app from chapter 1.
 * It includes:
 * - Buttons for changing the background color.
 * - Maintenance of instance state.
 * - Themes and styles.
 * - Read and write shared preferences for the current count and the color.
 *
 *
 * This is the solution code for HelloSharedPrefs.
 */
class MainActivity : AppCompatActivity() {
    // Current count
    private var mCount = 0
    // Current background color
    private var mColor: Int = 0

    companion object {
        // Key for current count
        private const val COUNT_KEY = "count"
        // Key for current color
        private const val COLOR_KEY = "color"
        // Name of shared preferences file
//        private const val sharedPrefFile = "com.example.android.hellosharedprefs"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mColor = ContextCompat.getColor(this,
                R.color.default_background)

        //mPreferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // Restore preferences
        mCount = sharedPreferences.getInt(COUNT_KEY, 0)
        count_textview.text = String.format("%s", mCount)
        mColor = sharedPreferences.getInt(COLOR_KEY, mColor)
        count_textview.setBackgroundColor(mColor)
    }

    /**
     * Handles the onClick for the background color buttons.  Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    fun changeBackground(view: View) {
        val color = (view.background as ColorDrawable).color
        count_textview.setBackgroundColor(color)
        mColor = color
    }

    /**
     * Handles the onClick for the Count button.  Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    fun countUp(view: View) {
        mCount++
        count_textview.text = String.format("%s", mCount)
    }

    /**
     * Handles the onClick for the Reset button.  Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    fun reset(view: View) {
        // Reset count
        mCount = 0
        count_textview.text = String.format("%s", mCount)

        // Reset color
        mColor = ContextCompat.getColor(this,
                R.color.default_background)
        count_textview.setBackgroundColor(mColor)

        // Clear preferences
        sharedPreferences.edit { clear() }
    }

    /**
     * Callback for activity pause.  Shared preferences are saved here.
     */
    override fun onPause() {
        super.onPause()

        sharedPreferences.edit {
            putInt(COUNT_KEY, mCount)
            putInt(COLOR_KEY, mColor)
        }

    }
}
