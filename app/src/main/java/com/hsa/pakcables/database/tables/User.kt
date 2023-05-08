package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val email: String,
    val dob: String,
    val isAdmin: Boolean,
    val hasAcceptedTerms: Boolean,
    val createdDate : Date,
    val lastUpdated : Date,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)