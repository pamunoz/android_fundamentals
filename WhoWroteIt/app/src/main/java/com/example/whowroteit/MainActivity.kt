package com.example.whowroteit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun searchBooks(view: View) {
        // Get the search string from the input field.
        val queryString = et_bookInput.text.toString()
        view.hideTheKeyBoard(this)
        if (isNetworkConnected && queryString.isNotEmpty()) {
            FetchBook(tv_titleText, tv_authorText).execute(queryString)
            tv_authorText.text = ""
            tv_titleText.text = resources.getString(R.string.loading)
        } else {
            if (queryString.isEmpty()) {
                tv_authorText.text = ""
                tv_titleText.text = resources.getString(R.string.no_search_term)
            } else {
                tv_authorText.text = ""
                tv_titleText.text = resources.getString(R.string.no_network)
            }
        }
    }

}
