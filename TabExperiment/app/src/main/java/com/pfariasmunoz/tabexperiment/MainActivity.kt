package com.pfariasmunoz.tabexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        tab_layout.apply {
            // Set the text for each tab.
            addTab((tab_layout.newTab().setText(R.string.tab_label1)))
            addTab((tab_layout.newTab().setText(R.string.tab_label2)))
            addTab((tab_layout.newTab().setText(R.string.tab_label3)))
            // Set the tabs to fill the entire layout.
            tabGravity = TabLayout.GRAVITY_FILL
        }
        // Use PagerAdapter to manage page views in fragments.
        // Setting a listener for clicks.
        pager.apply {
            adapter = PagerAdapter(supportFragmentManager, tab_layout.tabCount)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        }
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    pager.currentItem = tab.position
                }
            }

        })
    }
}
