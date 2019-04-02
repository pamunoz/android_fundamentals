package com.example.whowroteit

import android.net.Uri
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {
    private var LOG: String = NetworkUtils::class.java.simpleName
    // Base URL for Books API.
    private const val BOOK_BASE_URL: String = "https://www.googleapis.com/books/v1/volumes?"
    // Parameter for the search string.
    private const val QUERY_PARAM = "q"
    // Parameter that limits search results.
    private const val MAX_RESULTS = "maxResults"
    // Parameter to filter by print type.
    private const val PRINT_TYPE = "printType"


    fun getBookInfo(query: String): String {
        val urlConnection: HttpURLConnection
        val reader: BufferedReader
        val bookJSonString: String = ""
        try {
            val builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, query)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build()
            val requestUrl = URL(builtUri.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {

        }
        return bookJSonString
    }
}