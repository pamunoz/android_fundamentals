package com.pfariasmunoz.myrecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mWordList = LinkedList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Put initial data into the word list.
        for (i in 0..19) {
            mWordList.addLast("Word $i")
        }
        rv_words.adapter = WordListAdapter(this, mWordList)
        rv_words.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { view ->
            val wordListSize = mWordList.size
            mWordList.addLast("+ Word $wordListSize")
            (rv_words.adapter as WordListAdapter).notifyItemInserted(wordListSize)
            rv_words.smoothScrollToPosition(wordListSize)
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
