package com.example.whowroteitloader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , LoaderManager.LoaderCallbacks<String>{

    fun searchBooks(view: View) {
        // Get the search string from the input field.
        val queryString = et_bookInput.text.toString()
        view.hideTheKeyBoard(this)
        if (isNetworkConnected && queryString.isNotEmpty()) {
            val queryBundle = Bundle().apply {
                putString("queryString", queryString)
            }
            supportLoaderManager.restartLoader(0, queryBundle, this)

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

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoaderReset(loader: Loader<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
