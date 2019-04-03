package com.example.whowroteitloader

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

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
        var queryString: String = ""
        if (args != null) {
            queryString = args.getString("queryString"!!)
        }
        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        try {
            // Convert the response into a JSON object.
            val jsonObject: JSONObject = JSONObject(data)
            // Get the JSONArray of book items.
            val itemsArray: JSONArray = jsonObject.getJSONArray("items")
            var i = 0
            var title: String? = null
            var authors: String? = null
            while (i < itemsArray.length() && (authors == null && title == null)) {
                // Get the current item information
                val book: JSONObject = itemsArray.getJSONObject(i)

                val volumeInfo: JSONObject = book.getJSONObject("volumeInfo")

                // try to get the author and title from the current item
                // catch if either field is empty and move on
                try {
                    title = volumeInfo.getString("title")
                    authors = volumeInfo.getString("authors")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                // Move to the next item
                i++
            }

            // If both are found display the result
            if (title != null && authors != null) {
                tv_titleText.text = title
                tv_authorText.text = authors
            } else {
                // If none are found, update the UI to show failed results
                tv_titleText.text =resources.getString(R.string.no_result)
                tv_authorText.text = ""

            }

        } catch (e: JSONException) {
            // If postExecute does not receive a proper JSONString,
            // update the UI to show failed results
            tv_titleText.text = resources.getString(R.string.no_result)
            tv_authorText.text = ""
            e.printStackTrace()
        }
    }

    override fun onLoaderReset(loader: Loader<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
    }

}
