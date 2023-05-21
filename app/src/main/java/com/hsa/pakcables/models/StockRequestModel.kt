package com.hsa.pakcables.models

import com.hsa.pakcables.functions.getCurrentDate
import java.util.*


data class StockRequestModel (
     var red : Int = 0,
     var black : Int = 0,
     var yellow : Int = 0,
     var blue : Int = 0,
     var green : Int = 0,
     var white : Int = 0,
     var other : Int = 0,
     var total : Int = 0,
     var core2 : List<Double> = emptyList(),
     var core3 : List<Double> = emptyList(),
     var core4 : List<Double> = emptyList(),
     var core5 : List<Double> = emptyList(),
     var core6 : List<Double> = emptyList(),
     var itemName : String = "",
     val itemCodingID : Int = 0,
     val id: Int = 0,
     val createdDate : Date = getCurrentDate(),
     val lastUpdated : Date = getCurrentDate(),
)