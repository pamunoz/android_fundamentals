package com.pfariasmunoz.myrecyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter: RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

    }

    override fun getItemCount(): Int {
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
    }

    class WordViewHolder(itemView: View, val adapter: WordListAdapter): RecyclerView.ViewHolder(itemView) {

    }

}