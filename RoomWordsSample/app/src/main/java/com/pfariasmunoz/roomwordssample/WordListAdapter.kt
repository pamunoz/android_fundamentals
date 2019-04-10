package com.pfariasmunoz.roomwordssample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pfariasmunoz.roomwordssample.db.Word
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class WordListAdapter(val context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    var words: List<Word>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount() = words?.size ?: 0

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (words != null) {
            val current = words?.get(position)
            holder.wordView.text = current?.word
        } else {
            holder.wordView.text = context.resources.getString(R.string.no_word)
        }
    }

    inner class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val wordView: TextView = itemView.tv_word
    }
}