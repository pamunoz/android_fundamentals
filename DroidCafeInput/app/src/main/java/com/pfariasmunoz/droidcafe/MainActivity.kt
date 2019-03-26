package com.pfariasmunoz.droidcafe

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, mOrderMessage)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_contact -> {
                toast(R.string.action_contact_message)
                true
            }
            R.id.action_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage)
                startActivity(intent)
                true
            }
            R.id.action_status -> {
                toast(R.string.action_status_message)
                true
            }
            R.id.action_favorites -> {
                toast(R.string.action_favorites_message)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        var mOrderMessage = "No Order made"
        const val EXTRA_MESSAGE = "com.pfariasmunoz.droidcafe.extra.MESSAGE"
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    fun showDonutOrder(view: View) {
        mOrderMessage = resources.getString(R.string.donut_order_message)
        toast(mOrderMessage)
    }

    fun showIceCreamOrder(view: View) {
        mOrderMessage = resources.getString(R.string.ice_cream_order_message)
        toast(mOrderMessage)
    }

    fun showFroyoOrder(view: View) {
        mOrderMessage = resources.getString(R.string.froyo_order_message)
        toast(mOrderMessage)
    }

}
