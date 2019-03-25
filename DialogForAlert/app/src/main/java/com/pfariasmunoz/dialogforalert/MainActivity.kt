package com.pfariasmunoz.dialogforalert

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickShowAlert(view: View) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this).apply {
            title = "Alert"
            setMessage("Click OK to continue, or Cancel to stop:")
            setPositiveButton("Ok") { dialog, which ->  

            }
        }

    }
}
