package com.sedat.kotlinapp.fragment.getimage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedat.kotlinapp.adapter.FragmentStateAdapter
import com.sedat.kotlinapp.databinding.FragmentGetImageBinding
import com.sedat.kotlinapp.fragment.retrofit.CodesFragment
import com.sedat.kotlinapp.util.TabLayoutMed


class GetImageFragment : Fragment() {

    private var fragmentbinding: FragmentGetImageBinding ?= null
    private val _binding get() = fragmentbinding!!

    private lateinit var fragmentStateAdapter: FragmentStateAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentbinding = FragmentGetImageBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentStateAdapter = FragmentStateAdapter(this, UsageFragmentGetImage(), CodesFragment("PhoneImage"))
        _binding.viewPager.adapter = fragmentStateAdapter

        TabLayoutMed(_binding.tabLayout, _binding.viewPager).getTabs()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentbinding = null
    }


}