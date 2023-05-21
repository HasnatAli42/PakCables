package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Prices(
    val red : Int,
    val black : Int,
    val yellow : Int,
    val blue : Int,
    val green : Int,
    val white : Int,
    val other : Int,
    val total : Int,
    val core2 : Int,
    val core3 : Int,
    val core4 : Int,
    val core5 : Int,
    val core6 : Int,
    val createdDate : Date,
    val lastUpdated : Date,
    val itemName : String,
    val itemCodingID : Int,
    val userID : Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)