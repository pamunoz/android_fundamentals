package com.example.whowroteit

import android.net.Uri
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStream


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


    fun getBookInfo(query: String): String? {
        var urlConnection: HttpURLConnection? = null
        var bookJSONString: String? = null

        try {
            // Build the full query URI, limiting results to 10 items and
            // printed books.
            val builtUri = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, query)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build()

            // Convert the URI to a URL,
            val requestURL = URL(builtUri.toString())

            // Open the network connection.
            urlConnection = requestURL.openConnection() as HttpURLConnection
            urlConnection.apply {
                requestMethod = "GET"
            }.connect()

            // Get the InputStream.
            val inputStream = urlConnection.inputStream
            //if (builder.length == 0) ""
            bookJSONString = readInputStream(inputStream)

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            // Close the connection and the buffered reader.
            urlConnection?.disconnect()

        }
        Log.d(LOG, bookJSONString)
        return bookJSONString
    }

    private fun readInputStream(inputStream: InputStream): String? {
        // Create a buffered reader from that input stream.
        val reader = BufferedReader(InputStreamReader(inputStream))

        // Use a StringBuilder to hold the incoming response.
        val builder = StringBuilder()

        // Read the input line-by-line into the string while there is still input:
        while (reader.readLine() != null) {
            // Add the current line to the string.
            builder.append(reader.readLine())

            // Since it's JSON, adding a newline isn't necessary (it won't
            // affect parsing) but it does make debugging a *lot* easier
            // if you print out the completed buffer for debugging.
            builder.append("\n")
        }
        return if (builder.isEmpty()) return null else builder.toString()
    }
}