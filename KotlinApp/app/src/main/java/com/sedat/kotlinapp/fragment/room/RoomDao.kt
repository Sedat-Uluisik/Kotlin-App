package com.sedat.kotlinapp.fragment.room

import androidx.room.*
import com.sedat.kotlinapp.model.MyRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Save(myRoom: MyRoom)

    //@Query("DELETE FROM T_ROOM WHERE id = :id")
    @Query("DELETE FROM T_ROOM")
    suspend fun Delete()

    @Update
    suspend fun Update(myRoom: MyRoom)

    @Query("SELECT * FROM T_ROOM WHERE id = 1")
    fun GetData(): Flow<MyRoom>

    //Flow ile Room içerisindeki verileri observable olarak alabiliriz.
}