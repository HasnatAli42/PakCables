package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.Prices
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface PricesDao {
    @Upsert
    suspend fun upsertPrices(prices: Prices)

    @Delete
    suspend fun deletePrices(prices: Prices)

    @Query("SELECT * FROM prices WHERE userID = :userID ORDER BY id ASC")
    fun getPrices(userID : Int): Flow<List<Prices>>

    @Query("UPDATE prices SET itemName = :itemName and lastUpdated = :lastUpdated WHERE id = :id")
    fun updatePricesItemName(itemName:String, lastUpdated : Date, id:Int)
}