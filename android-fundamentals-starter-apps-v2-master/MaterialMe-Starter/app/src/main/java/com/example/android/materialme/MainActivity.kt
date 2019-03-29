/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.materialme

import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/***
 * Main Activity for the Material Me app, a mock sports news application
 * with poor design choices.
 */
class MainActivity : AppCompatActivity() {

    // Member variables.
    private val mSportsData = ArrayList<Sport>()
    private var mAdapter: SportsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ArrayList that will contain the data.


        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = SportsAdapter(this, mSportsData)

        recyclerView.apply {
            // Set the Layout Manager.
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }


        // Get the data.
        initializeData()

        ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or
                        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder) : Boolean {
                val from = viewHolder.adapterPosition
                val to = viewHolder.adapterPosition
                Collections.swap(mSportsData, from, to)
                mAdapter!!.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
                                  direction: Int) {
                mSportsData.removeAt(viewHolder.adapterPosition)
                mAdapter!!.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(recyclerView)
    }

    /**
     * Initialize the sports data from resources.
     */
    private fun initializeData() {
        // Get the resources from the XML file.
        val sportsList = resources.getStringArray(R.array.sports_titles)
        val sportsInfo = resources.getStringArray(R.array.sports_info)

        // Clear the existing data (to avoid duplication).
        mSportsData.clear()

        val sportImageResources = resources.obtainTypedArray(R.array.sports_images)

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (i in 0 until sportsList.size) {
            mSportsData.add(Sport(sportsList[i], sportsInfo[i], sportImageResources.getResourceId(i, 0)))
        }

        sportImageResources.recycle()

        // Notify the adapter of the change.
        mAdapter!!.notifyDataSetChanged()
    }

    fun resetSports(view: View) {
        
    }

}
