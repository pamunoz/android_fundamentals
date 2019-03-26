package com.pfariasmunoz.myrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class WordListAdapter(ctx: Context, private val wordList: LinkedList<String>): RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = wordList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        (holder.itemView as TextView).text = wordList[position]
    }

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}