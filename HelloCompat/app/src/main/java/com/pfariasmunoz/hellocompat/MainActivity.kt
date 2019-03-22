package com.pfariasmunoz.hellocompat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // restore saved instance state (the text color)
        if (savedInstanceState != null) tv_hello.setTextColor(savedInstanceState.getInt(COLOR_KEY))
    }

    fun changeColor(view: View) {
        val colorName = mColorArray[Random.nextInt(20)]
        val colorResName = resources.getIdentifier(colorName, "color", applicationContext.packageName)
        val colorRes: Int = ContextCompat.getColor(this, colorResName)
        tv_hello.setTextColor(colorRes)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        // save the current text color
        outState?.putInt(COLOR_KEY, tv_hello.currentTextColor)
    }

    companion object {
        val mColorArray = arrayOf("red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black")

        private const val COLOR_KEY = "com.pfariasmunoz.hellocompat.color"
    }
}
