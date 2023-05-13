package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ItemCoding(
    val name : String,
    val userId : Int,
    var sortOrder  : Double,
    val createdDate : Date,
    var lastUpdated : Date,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)