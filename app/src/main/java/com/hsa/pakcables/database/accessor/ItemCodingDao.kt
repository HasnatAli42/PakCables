package com.hsa.pakcables.database.accessor

import androidx.room.*
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemCodingDao {
    @Upsert
    suspend fun upsertItemCoding(itemCoding: ItemCoding)

    @Insert
    suspend fun insertItemCoding(itemCoding: ItemCoding) : Long

    @Delete
    suspend fun deleteItemCoding(itemCoding: ItemCoding)

    @Query("SELECT * FROM itemCoding WHERE userID = :userID ORDER BY sortOrder ASC")
    fun getItemCoding(userID : Int): Flow<List<ItemCoding>>
}