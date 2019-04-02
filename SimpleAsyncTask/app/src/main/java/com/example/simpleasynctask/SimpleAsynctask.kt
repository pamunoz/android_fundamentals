package com.example.simpleasynctask

import android.os.AsyncTask
import android.widget.TextView
import java.lang.ref.WeakReference

class SimpleAsynctask(textView: TextView) : AsyncTask<Any, Any, String>() {

    /** The AsyncTask needs to update the TextView in the Activity once it has
     * completed sleeping (in the onPostExecute() method). The constructor for
     * the class will therefore need a reference to the TextView to be updated.  */
    private val mTextView: WeakReference<TextView> = WeakReference(textView)

    override fun doInBackground(vararg params: Any?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}