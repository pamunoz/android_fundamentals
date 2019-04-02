package com.example.whowroteit

import android.os.AsyncTask
import android.widget.TextView
import java.lang.ref.WeakReference

class FetchBook(titleText: TextView, authorText: TextView): AsyncTask<String, Unit, String>() {

    private val mTitleText: WeakReference<TextView> = WeakReference(titleText)
    private val mAuthorText: WeakReference<TextView> = WeakReference(authorText)

    override fun doInBackground(vararg params: String?): String {
        return ""
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}