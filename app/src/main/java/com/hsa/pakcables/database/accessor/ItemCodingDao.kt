package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemCodingDao {
    @Upsert
    suspend fun upsertItemCoding(itemCoding: ItemCoding)

    @Delete
    suspend fun deleteItemCoding(itemCoding: ItemCoding)

    @Query("SELECT * FROM itemCoding ORDER BY id ASC")
    fun getItemCoding(): Flow<List<ItemCoding>>
}