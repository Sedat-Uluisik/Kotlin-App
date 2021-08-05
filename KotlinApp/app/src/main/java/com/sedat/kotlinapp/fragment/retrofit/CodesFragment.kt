package com.sedat.kotlinapp.fragment.retrofit

import android.graphics.PointF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.android.material.tabs.TabLayout
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.adapter.CodesAdapter
import com.sedat.kotlinapp.databinding.FragmentCodesBinding
import com.sedat.kotlinapp.viewmodel.FirebaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CodesFragment(val pathName: String) : Fragment() {

    private var fragmentBinding: FragmentCodesBinding ?= null
    private val _binding get() = fragmentBinding!!

    private lateinit var viewModel: FirebaseViewModel

    @Inject
    lateinit var adapter: CodesAdapter
    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentCodesBinding.inflate(inflater, container, false)
        val view = _binding.root
        viewModel = ViewModelProvider(requireActivity()).get(FirebaseViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData(pathName)

        _binding.recylerCodes.adapter = adapter
        _binding.recylerCodes.layoutManager = LinearLayoutManager(view.context)

        adapter.setOnImageClickListener {
            _binding.recylerCodes.visibility = View.GONE
            _binding.zoomImage.visibility = View.VISIBLE
            glide.load(it).into(_binding.zoomImage)
        }

        _binding.zoomImage.setOnClickListener {
            if(it.isVisible){
                it.visibility = View.GONE
                _binding.recylerCodes.visibility = View.VISIBLE
            }
        }

        getData()
    }

    private fun getData(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.items = it

                adapter.refresh()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}