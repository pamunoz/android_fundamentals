package com.example.simpleasynctask

import android.os.AsyncTask
import android.widget.TextView
import java.lang.ref.WeakReference
import java.util.*

class SimpleAsynctask(textView: TextView) : AsyncTask<Any, Any, String>() {

    /** The AsyncTask needs to update the TextView in the Activity once it has
     * completed sleeping (in the onPostExecute() method). The constructor for
     * the class will therefore need a reference to the TextView to be updated.  */
    private val mTextView: WeakReference<TextView> = WeakReference(textView)

    override fun doInBackground(vararg params: Any?): String {
        // Generate a random number between 0 and 10
        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        val s = Random().nextInt(11) * 200L
        try {
            Thread.sleep(s)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        // Return a String result
        return "Awake at last after sleeping for $s milliseconds"
    }

    /**
     *  The String parameter to this method is what you defined in the third parameter of your
     *  AsyncTask class definition, and what your doInBackground() method returns.
     *  Because mTextView is a weak reference, you have to deference it with the get() method
     *  to get the underlying TextView object, and to call setText() on it
     */
    override fun onPostExecute(result: String?) {
        mTextView.get()?.text = result
    }

}