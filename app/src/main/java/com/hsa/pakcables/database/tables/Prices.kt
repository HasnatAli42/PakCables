package com.hsa.pakcables.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Prices(
    var red : Int,
    var black : Int,
    var yellow : Int,
    var blue : Int,
    var green : Int,
    var white : Int,
    var other : Int,
    var total : Int,
    var core2 : Int,
    var core3 : Int,
    var core4 : Int,
    var core5 : Int,
    var core6 : Int,
    val createdDate : Date,
    val lastUpdated : Date,
    val itemName : String,
    val itemCodingID : Int,
    val userID : Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)