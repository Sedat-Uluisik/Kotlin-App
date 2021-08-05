package com.sedat.kotlinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.sedat.kotlinapp.model.FirebaseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val fireStore: FirebaseFirestore
): ViewModel() {

    private var items: List<FirebaseItem> = ArrayList()
    private val images = MutableLiveData<List<FirebaseItem>>()
    var imageList: LiveData<List<FirebaseItem>>
        get() = images
        private set(value) {  //Datanın temizlenmesinde kullanılıyor.
            val value1 = images.value
        }

    fun getData(pathName: String){

        clearData()
        fireStore.collection("KotlinApp")
            .document("KotlinApp01")
            .collection(pathName).addSnapshotListener { value, error ->
            if(error != null){
                println(error.localizedMessage)
                return@addSnapshotListener
            }else{
                if(value != null && !value.isEmpty){
                    (items as ArrayList<FirebaseItem>).clear()
                    val documents = value.documents
                    for (document in documents){
                        val _firebaseItem = document.toObject(FirebaseItem::class.java) as FirebaseItem
                        (items as ArrayList<FirebaseItem>).add(_firebaseItem)
                    }
                    images.value = items
                }else{
                    clearData()
                }
            }
        }

    }

    //Fragment arası geçişte dataların karışmasını engellemek için.
    fun clearData(){
        images.value = listOf()
        (items as ArrayList<FirebaseItem>).clear()
        imageList = imageList
    }

}