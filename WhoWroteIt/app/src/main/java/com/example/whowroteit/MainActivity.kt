package com.example.whowroteit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun searchBooks(view: View) {
        // Get the search string from the input field.
        val queryString = et_bookInput.text.toString()
        val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        FetchBook(tv_titleText, tv_authorText).execute(queryString)
    }
}
