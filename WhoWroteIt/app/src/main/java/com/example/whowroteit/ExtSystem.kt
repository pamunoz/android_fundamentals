package com.example.whowroteit

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideTheKeyBoard(context: Context) {
    val inputManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}