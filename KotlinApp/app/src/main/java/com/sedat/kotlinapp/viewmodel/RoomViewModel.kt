package com.sedat.kotlinapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.sedat.kotlinapp.fragment.room.RoomDao
import com.sedat.kotlinapp.model.MyRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val dao: RoomDao,
    @ApplicationContext private val application: Context
): BaseViewModel(application as Application) {

    fun Save(myRoom: MyRoom){
        launch {
            dao.Save(myRoom)
        }
    }

    fun Update(myRoom: MyRoom){
        launch {
            dao.Update(myRoom)
        }
    }

    fun Delete(){
        launch {
            dao.Delete()
        }
    }

    val myText = dao.GetData().asLiveData()

}