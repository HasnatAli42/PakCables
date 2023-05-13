package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.User
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentUserDao {
    @Upsert
    suspend fun upsertUser(user : CurrentUser)

    @Delete
    suspend fun deleteUser(user : CurrentUser)

    @Query("SELECT * FROM currentUser ORDER BY id DESC")
    fun getUserById(): Flow<List<CurrentUser>>

    @Query("SELECT * FROM currentUser ORDER BY firstName ASC")
    fun getUserByFirstName(): Flow<List<CurrentUser>>
}