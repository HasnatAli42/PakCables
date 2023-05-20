package com.hsa.pakcables.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hsa.pakcables.database.accessor.*
import com.hsa.pakcables.database.tables.*
import com.hsa.pakcables.functions.Converters

@Database(
    entities = [User::class, Input::class, ItemCoding::class, PartyCoding::class, CurrentUser::class],
    version = 13,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class StockDataBase: RoomDatabase() {

    abstract val userDao: UserDao
    abstract val currentUserDao: CurrentUserDao
    abstract val inputDao: InputDao
    abstract val itemCodingDao: ItemCodingDao
    abstract val partyCodingDao: PartyCodingDao
}