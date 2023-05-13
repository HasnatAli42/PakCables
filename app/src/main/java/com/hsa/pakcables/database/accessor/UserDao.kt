package com.hsa.pakcables.database.accessor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hsa.pakcables.database.tables.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)

    @Query("SELECT * FROM user ORDER BY userName ASC")
    fun getUserByUserName(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?
}