package com.hsa.pakcables.models

import com.hsa.pakcables.functions.getCurrentDate
import java.util.*


data class PricesRequestModel (
     var red : Int = 1,
     var black : Int = 1,
     var yellow : Int = 1,
     var blue : Int = 1,
     var green : Int = 1,
     var white : Int = 1,
     var other : Int = 1,
     var total : Int = 1,
     var core2 : Int = 1,
     var core3 : Int = 1,
     var core4 : Int = 1,
     var core5 : Int = 1,
     var core6 : Int = 1,
     val createdDate : Date = getCurrentDate(),
     val lastUpdated : Date = getCurrentDate(),
     var itemName : String = "",
     val itemCodingID : Int = 0,
     val id: Int = 0,
)
