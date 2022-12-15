package com.sartor.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CouponsViewPagerAdapter(private val items: ArrayList<Fragment>, fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return items[0]
            }
            1 -> {
                return items[1]
            }
            else -> items[0]
        }
    }
}