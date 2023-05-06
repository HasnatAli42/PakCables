package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val core2 : Float,
    val core3 : Float,
    val core4 : Float,
    val core5 : Float,
    val core6 : Float,
    val createdDate : String,
    val lastUpdated : String,
    val inputID : Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
)