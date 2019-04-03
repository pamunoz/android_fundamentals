package com.example.whowroteitloader

import android.content.Context
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context): AsyncTaskLoader<String>(context) {



    override fun loadInBackground(): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}