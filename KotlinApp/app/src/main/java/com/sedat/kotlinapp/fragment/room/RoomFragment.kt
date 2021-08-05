package com.sedat.kotlinapp.fragment.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.adapter.FragmentStateAdapter
import com.sedat.kotlinapp.databinding.FragmentRoomBinding
import com.sedat.kotlinapp.fragment.retrofit.CodesFragment
import com.sedat.kotlinapp.util.TabLayoutMed
import com.sedat.kotlinapp.viewmodel.RoomViewModel

class RoomFragment : Fragment() {

    private var fragmentBinding: FragmentRoomBinding ?= null
    private val _binding get() = fragmentBinding!!

    private lateinit var fragmentStateAdapter: FragmentStateAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentRoomBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentStateAdapter = FragmentStateAdapter(this, UsageFragmentRoom(), CodesFragment("Room"))
        _binding.viewPager.adapter = fragmentStateAdapter

        TabLayoutMed(_binding.tabLayout, _binding.viewPager).getTabs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }


}