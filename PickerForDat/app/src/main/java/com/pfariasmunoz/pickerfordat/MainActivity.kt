package com.pfariasmunoz.pickerfordat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showDatePicker(view: View) {
        DatePickerFragment().show(supportFragmentManager, "datePicker")
    }

    fun processDatePickerResult(year: Int, month: Int, day: Int) {
        val message: String = "${month + 1}/$day/$year"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
