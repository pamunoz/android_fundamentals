package com.example.whowroteitloader

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import java.lang.ref.WeakReference

class FetchBook(titleText: TextView, authorText: TextView): AsyncTask<String, Unit, String>() {

    private val mTitleText: WeakReference<TextView> = WeakReference(titleText)
    private val mAuthorText: WeakReference<TextView> = WeakReference(authorText)

    override fun doInBackground(vararg params: String?) = NetworkUtils.getBookInfo(params[0]!!)

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d("pablo", result)
        try {
            // Convert the response into a JSON object.
            val jsonObject: JSONObject = JSONObject(result)
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
                mTitleText.get()?.text = title
                mAuthorText.get()?.text = authors
            } else {
                // If none are found, update the UI to show failed results
                mTitleText.get()?.text = mTitleText.get()?.resources?.getString(R.string.no_result)
                mAuthorText.get()?.text = ""

            }

        } catch (e: JSONException) {
            // If postExecute does not receive a proper JSONString,
            // update the UI to show failed results
            mTitleText.get()?.text = mTitleText.get()?.resources?.getString(R.string.no_result)
            mAuthorText.get()?.text = ""
            e.printStackTrace()
        }

    }
}