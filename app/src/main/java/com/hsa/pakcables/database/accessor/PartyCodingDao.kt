package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.database.tables.User
import kotlinx.coroutines.flow.Flow

@Dao
interface PartyCodingDao {
    @Upsert
    suspend fun upsertPartyCoding(partyCoding: PartyCoding)

    @Delete
    suspend fun deletePartyCoding(partyCoding: PartyCoding)

    @Query("SELECT * FROM itemCoding ORDER BY id ASC")
    fun getPartyCoding(): Flow<List<PartyCoding>>
}