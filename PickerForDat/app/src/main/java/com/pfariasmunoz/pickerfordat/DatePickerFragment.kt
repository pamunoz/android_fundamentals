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

    /**
     * Creates the date picker dialog with the current date from Calendar.
     *
     * @param savedInstanceState    Saved instance state bundle
     * @return DatePickerDialog     The date picker dialog
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(activity, this, year, month, day)
    }

    /**
     * Grabs the date and passes it to processDatePickerResult().
     *
     * @param datePicker  The date picker view
     * @param year  The year chosen
     * @param month The month chosen
     * @param day   The day chosen
     */
    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, day: Int) {
        // Use the current date as the default date in the picker.
        // Set the activity to the Main Activity.
        (activity as MainActivity).processDatePickerResult(year, month, day)
    }


}
