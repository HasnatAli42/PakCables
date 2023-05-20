package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.User
import kotlinx.coroutines.flow.Flow

@Dao
interface InputDao {
    @Upsert
    suspend fun upsertInput(input : Input)

    @Delete
    suspend fun deleteInput(input : Input)

    @Query("SELECT * FROM input WHERE userID = :userID ORDER BY inputID ASC")
    fun getInput(userID : Int): Flow<List<Input>>

    @Query("SELECT * FROM input WHERE userID = :userID and inputID = :inputID ORDER BY inputID ASC")
    fun getInputByID(userID : Int, inputID : Int): Flow<List<Input>>
}