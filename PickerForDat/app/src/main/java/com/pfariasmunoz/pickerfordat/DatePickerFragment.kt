package com.pfariasmunoz.pickerfordat


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onDateSet(datePicker: DatePicker?, y: Int, m: Int, d: Int) {
        // Use the current date as the default date in the picker.
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        DatePickerDialog(MainActivity@activity, this, year, month, day)
    }


}
