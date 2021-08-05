package com.sedat.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.kotlinapp.api.RetrofitApi
import com.sedat.kotlinapp.model.RetrofitImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val retrofit: RetrofitApi
): ViewModel() {

    private val imageList = MutableLiveData<RetrofitImageResponse>()
    val images: LiveData<RetrofitImageResponse>
        get() = imageList

    fun getImages(){
        viewModelScope.launch {
            try {
                val response = retrofit.getImages()
                if(response.isSuccessful){
                    response.body()?.let {
                        imageList.value = it
                    }
                }else
                    println(response.message())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}