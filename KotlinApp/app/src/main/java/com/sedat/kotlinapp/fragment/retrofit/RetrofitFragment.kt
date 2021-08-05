package com.sedat.kotlinapp.fragment.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.adapter.FragmentStateAdapter
import com.sedat.kotlinapp.adapter.RetrofitAdapter
import com.sedat.kotlinapp.databinding.FragmentRetrofitBinding
import com.sedat.kotlinapp.util.TabLayoutMed
import com.sedat.kotlinapp.viewmodel.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class RetrofitFragment : Fragment() {

    private var fragmentBinding: FragmentRetrofitBinding ?= null
    private val _binding get() = fragmentBinding!!

    private lateinit var fragmentStateAdapter: FragmentStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentRetrofitBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Create swipe views with tabs using ViewPager2
        fragmentStateAdapter = FragmentStateAdapter(this, UsageFragment(), CodesFragment("Retrofit"))
        _binding.viewPager.adapter = fragmentStateAdapter

        TabLayoutMed(_binding.tabLayout, _binding.viewPager).getTabs()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}