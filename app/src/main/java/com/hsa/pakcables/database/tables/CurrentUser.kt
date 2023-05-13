package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class CurrentUser(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val dob: String,
    val isAdmin: Boolean,
    val hasAcceptedTerms: Boolean,
    val isRemembered: Boolean,
    val isLoggedIn: Boolean,
    val userID: Int,
    val createdDate: Date,
    val lastUpdated: Date,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 1,
)