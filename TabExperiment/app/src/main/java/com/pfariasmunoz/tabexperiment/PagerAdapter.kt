package com.pfariasmunoz.tabexperiment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PagerAdapter(fm: FragmentManager, private val tabNum: Int): FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FirstTabFragment()
            1 -> SecondTabFragment()
            2 -> ThirdTabFragment()
            else -> Fragment()
        }
    }

    override fun getCount() = tabNum

}