package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Input(
    val red : Int,
    val black : Int,
    val yellow : Int,
    val blue : Int,
    val green : Int,
    val white : Int,
    val other : Int,
    val total : Int,
    val core2 : Double,
    val core3 : Double,
    val core4 : Double,
    val core5 : Double,
    val core6 : Double,
    val createdDate : Date,
    val lastUpdated : Date,
    val itemName : String,
    val itemCodingID : Int,
    val inputID : Int,
    val userID : Int,
    val remarks : String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)