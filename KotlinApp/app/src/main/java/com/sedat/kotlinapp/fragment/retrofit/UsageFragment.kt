package com.sedat.kotlinapp.fragment.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.sedat.kotlinapp.adapter.RetrofitAdapter
import com.sedat.kotlinapp.databinding.FragmentRetrofitUsageBinding
import com.sedat.kotlinapp.viewmodel.RetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsageFragment : Fragment() {

    private var fragmentBinding: FragmentRetrofitUsageBinding?= null
    private val _binding get() = fragmentBinding!!

    private lateinit var viewModel: RetrofitViewModel

    @Inject
    lateinit var adapter: RetrofitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentRetrofitUsageBinding.inflate(inflater, container, false)
        val view = _binding.root
        viewModel = ViewModelProvider(requireActivity()).get(RetrofitViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        _binding.recylerUsage.adapter = adapter
        _binding.recylerUsage.layoutManager = GridLayoutManager(requireContext(),3)

        viewModel.getImages()
        observeData()
    }

    private fun observeData(){
        viewModel.images.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.images = it.hits
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }

    
}