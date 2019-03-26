package com.pfariasmunoz.tabexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar)
        (tab_layout as TabLayout).apply {
            // Set the text for each tab.
            addTab(((tab_layout as TabLayout).newTab().setText(R.string.tab_label1)))
            addTab(((tab_layout as TabLayout).newTab().setText(R.string.tab_label2)))
            addTab(((tab_layout as TabLayout).newTab().setText(R.string.tab_label3)))
            // Set the tabs to fill the entire layout.
            (tab_layout as TabLayout).tabGravity = TabLayout.GRAVITY_FILL
            // Use PagerAdapter to manage page views in fragments.
        }
    }
}
