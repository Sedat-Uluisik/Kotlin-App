package com.sedat.kotlinapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_ROOM")
data class MyRoom(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var text: String
)