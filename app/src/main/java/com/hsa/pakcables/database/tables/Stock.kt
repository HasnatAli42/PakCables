package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Stock(
    val red : Int,
    val black : Int,
    val yellow : Int,
    val blue : Int,
    val green : Int,
    val white : Int,
    val other : Int,
    val total : Int,
    val core2 : List<Double>,
    val core3 : List<Double>,
    val core4 : List<Double>,
    val core5 : List<Double>,
    val core6 : List<Double>,
    val createdDate : Date,
    val lastUpdated : Date,
    val itemName : String,
    val itemCodingID : Int,
    val userID : Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)