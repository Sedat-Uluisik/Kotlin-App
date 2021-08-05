package com.sedat.kotlinapp.fragment.workmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.databinding.FragmentWorkManagerBinding
import com.sedat.kotlinapp.fragment.retrofit.CodesFragment
import com.sedat.kotlinapp.util.TabLayoutMed

class WorkManagerFragment : Fragment() {

    private var fragmentBinding: FragmentWorkManagerBinding ?= null
    private val _binding get() = fragmentBinding!!

    private lateinit var fragmentStateAdapter: FragmentStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentWorkManagerBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentStateAdapter = com.sedat.kotlinapp.adapter.FragmentStateAdapter(this, UsageWorkManagerFragment(), CodesFragment("WorkManager"))
        _binding.viewPager.adapter = fragmentStateAdapter

        TabLayoutMed(_binding.tabLayout, _binding.viewPager).getTabs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }


}