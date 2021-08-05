package com.sedat.kotlinapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentStateAdapter(fragment: Fragment, val fragmentUsage: Fragment, val fragmentCodes: Fragment): FragmentStateAdapter(fragment) {

    //Tablayout içinde gösterilecek fragmentler için kullanıldı.

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return fragmentUsage
            1 -> return fragmentCodes

            else -> return fragmentCodes
        }
    }

}