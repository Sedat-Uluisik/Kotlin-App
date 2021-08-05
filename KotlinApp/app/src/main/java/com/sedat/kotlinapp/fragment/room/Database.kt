package com.sedat.kotlinapp.fragment.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sedat.kotlinapp.model.MyRoom

@Database(entities = [MyRoom::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun roomDao(): RoomDao
}