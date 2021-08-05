package com.sedat.kotlinapp.util

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sedat.kotlinapp.R

class TabLayoutMed(val tabLayout: TabLayout, val viewPager2: ViewPager2) {
    fun getTabs(){
        TabLayoutMediator(tabLayout, viewPager2){ tab, position ->
            if(position == 0){
                tab.text = "Kullanımı"
            }
            else if(position == 1){
                tab.text = "Kaynak Kodları"
            }
        }.attach()
    }
}