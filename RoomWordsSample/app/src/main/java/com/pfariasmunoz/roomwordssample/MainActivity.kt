package com.pfariasmunoz.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pfariasmunoz.roomwordssample.db.Word
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }

    private var mViewModel: WordViewModel? = null
    private var mWordAdapter : WordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val newWordIntent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(newWordIntent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
        mWordAdapter = WordListAdapter(this@MainActivity)
        mViewModel = ViewModelProviders.of(this ).get(WordViewModel::class.java).apply {
            allWords.observe(this@MainActivity, Observer {
                mWordAdapter?.words = it
            })
        }
        rv_words.apply {
            adapter = mWordAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        enableSwipeWord()
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
            R.id.clear_data -> {
                // Add a toast just for confirmation
                Toast.makeText(this, "Clearing the data...", Toast.LENGTH_SHORT).show()
                // Delete the existing data
                mViewModel?.deleteAll()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE) and (resultCode == Activity.RESULT_OK)) {
            if (data != null) {
                mViewModel?.insert(Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY)))
            } else {
                Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun enableSwipeWord() {
        // Add the functionality to swipe items in the
        // recycler view to delete that item
        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder, tg: RecyclerView.ViewHolder)  = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val myWord = mWordAdapter?.getWordAtPosition(position)
                Toast.makeText(this@MainActivity, "Deleting ${myWord?.word}", Toast.LENGTH_LONG).show()
                // Delete the word
                myWord?.let { mViewModel?.deleteWord(it) }
            }

        }).attachToRecyclerView(rv_words)
    }
}
