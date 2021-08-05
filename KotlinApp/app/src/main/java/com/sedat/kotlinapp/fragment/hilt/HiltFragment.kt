package com.sedat.kotlinapp.fragment.hilt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.databinding.FragmentHiltBinding
import com.sedat.kotlinapp.fragment.retrofit.CodesFragment

class HiltFragment : Fragment() {

    private var fragmentBinding: FragmentHiltBinding ?= null
    private val _binding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentHiltBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction().replace(R.id.frame_layout, CodesFragment("Hilt")).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}