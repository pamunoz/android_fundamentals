package com.pfariasmunoz.implicitintents

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openWebsite(view: View) {
        // Get the URL text.
        val url = et_website.text.toString()
        // Parse the URI and create the intent.
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        // Find an activity to hand the intent and start that activity.
        startImplicitIntent(intent)
    }

    fun openLocation(view: View) {
        val addressUri: Uri = Uri.parse("geo:0,0?q=${et_location.text}")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)
        startImplicitIntent(intent)
    }

    fun shareText(view: View) {
        ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
            .setChooserTitle(resources.getString(R.string.share_chooser_title))
            .setText(et_share.text.toString())
            .startChooser()
    }
}
