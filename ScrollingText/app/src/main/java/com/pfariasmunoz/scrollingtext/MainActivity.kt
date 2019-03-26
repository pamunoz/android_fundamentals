package com.pfariasmunoz.scrollingtext

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerForContextMenu(tv_article)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
       return when(item?.itemId) {
            R.id.action_edit -> {
                toast("Edit choice Clicked")
                true
            }
            R.id.action_share -> {
                toast("Share choice Clicked")
                true
            }
            R.id.action_delete -> {
                toast("Delete choice Clicked")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}
