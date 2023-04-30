package com.hsa.pakcables.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsa.pakcables.database.accessor.UserDao
import com.hsa.pakcables.database.tables.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class StockDataBase: RoomDatabase() {

    abstract val dao: UserDao
}