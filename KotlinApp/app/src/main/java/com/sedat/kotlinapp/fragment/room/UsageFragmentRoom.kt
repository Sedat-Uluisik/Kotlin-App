package com.sedat.kotlinapp.fragment.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sedat.kotlinapp.R
import com.sedat.kotlinapp.adapter.FragmentStateAdapter
import com.sedat.kotlinapp.databinding.FragmentUsageRoomBinding
import com.sedat.kotlinapp.model.MyRoom
import com.sedat.kotlinapp.viewmodel.RoomViewModel

class UsageFragmentRoom : Fragment() {

    private var fragmentBinding: FragmentUsageRoomBinding ?= null
    private val _binding get() = fragmentBinding!!

    private lateinit var viewModel: RoomViewModel
    private lateinit var myRoom: MyRoom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = FragmentUsageRoomBinding.inflate(inflater, container, false)
        val view = _binding.root
        viewModel = ViewModelProvider(requireActivity()).get(RoomViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.btnSave.setOnClickListener {
            myRoom = MyRoom(1, _binding.edittext.text.toString())
            viewModel.Save(myRoom)
            _binding.edittext.setText("")
        }

        _binding.btnDelete.setOnClickListener {
            viewModel.Delete()
            _binding.textView.text = "---"
            _binding.edittext.setText("")
        }

        _binding.btnUpdate.setOnClickListener {
            myRoom = MyRoom(1, _binding.edittext.text.toString())
            viewModel.Update(myRoom)
        }

        getData()
    }

    private fun getData(){
        viewModel.myText.observe(viewLifecycleOwner, Observer {
            it?.let {
                _binding.textView.text = it.text
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }


}