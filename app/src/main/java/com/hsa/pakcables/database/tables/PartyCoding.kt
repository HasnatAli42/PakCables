package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class PartyCoding(
    val name : String,
    val userId : Int,
    var sortOrder  : Double,
    val createdDate : Date,
    var lastUpdated : Date,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)