package com.pfariasmunoz.keyboarddialphone

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        et_phone_send.setOnEditorActionListener { _, actionId, _ ->  if(actionId == EditorInfo.IME_ACTION_SEND) dialNumber() else false }
    }

    private fun dialNumber(): Boolean {

        // If the editText field is not null,
        // concatenate "tel: " with the phone number string.
        val phone = et_phone_send.text.toString()
        val phoneNumber: String = "tel:$phone"
        // Optional: Log the concatenated phone number for dialing.
        Log.d("MainActivity", "dialNumber: $phoneNumber")
        // Specify the intent.
        val dialIntent = Intent(Intent.ACTION_DIAL)
        // Set the data for the intent as the phone number.
        dialIntent.data = Uri.parse(phoneNumber)
        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if (dialIntent.resolveActivity(packageManager) != null) {
            startActivity(dialIntent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this")
        }
        return true
    }

}
