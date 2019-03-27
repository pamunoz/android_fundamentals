package com.pfariasmunoz.myrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wordlist_item.view.*
import java.util.*

class WordListAdapter(ctx: Context, private val wordList: LinkedList<String>): RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = wordList.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.textView.text = wordList[position]
    }

    inner class WordViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            // Get the position of the item that was clicked.
            val element = wordList[layoutPosition]
            wordList[layoutPosition] = "Clicked! $element"
            notifyDataSetChanged()
        }

        val textView = view.tv_word!!
    }

}