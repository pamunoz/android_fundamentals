package com.pfariasmunoz.pickerfordat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showDatePicker(view: View) {
        DatePickerFragment().show(supportFragmentManager, "datePicker")
    }

    companion object {
        fun processDatePickerResult(year: Int, month: Int, day: Int) {

        }
    }
}
