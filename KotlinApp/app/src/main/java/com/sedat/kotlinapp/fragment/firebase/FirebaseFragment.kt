package com.sedat.kotlinapp.fragment.firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.databinding.FragmentFirebaseBinding
import com.sedat.kotlinapp.fragment.retrofit.CodesFragment

class FirebaseFragment : Fragment() {

    private var fragmentBinding: FragmentFirebaseBinding ?= null
    private val _binding get() = fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentFirebaseBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction().replace(R.id.frame_layout, CodesFragment("Firebase")).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }


}