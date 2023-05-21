package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.Stock
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface StockDao {
    @Upsert
    suspend fun upsertStock(stock : Stock)

    @Delete
    suspend fun deleteStock(stock : Stock)

    @Query("SELECT * FROM stock WHERE userID = :userID ORDER BY id ASC")
    fun getStock(userID : Int): Flow<List<Stock>>

    @Query("UPDATE stock SET itemName = :itemName and lastUpdated = :lastUpdated WHERE id = :id")
    fun updateStockItemName(itemName:String, lastUpdated: Date, id:Int)
}