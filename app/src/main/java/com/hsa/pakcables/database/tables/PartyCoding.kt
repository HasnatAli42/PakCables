package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PartyCoding(
    val name : String,
    val createdDate : String,
    val lastUpdated : String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
)