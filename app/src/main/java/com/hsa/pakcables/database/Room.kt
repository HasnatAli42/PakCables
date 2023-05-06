package com.hsa.pakcables.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsa.pakcables.database.accessor.InputDao
import com.hsa.pakcables.database.accessor.ItemCodingDao
import com.hsa.pakcables.database.accessor.PartyCodingDao
import com.hsa.pakcables.database.accessor.UserDao
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.database.tables.User

@Database(
    entities = [User::class, Input::class, ItemCoding::class, PartyCoding::class],
    version = 3,
    exportSchema = false
)
abstract class StockDataBase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val inputDao: InputDao
    abstract val itemCodingDao: ItemCodingDao
    abstract val partyCodingDao: PartyCodingDao
}