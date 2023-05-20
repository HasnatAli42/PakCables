package com.hsa.pakcables.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hsa.pakcables.components.singletons.Conversion

data class InputRequestModel (
     val itemName : String = "",
     val itemID : Int = 0,
     var red : Int = 0,
     var black : Int = 0,
     var yellow : Int = 0,
     var blue : Int = 0,
     var green : Int = 0,
     var white : Int = 0,
     var other : Int = 0,
     var total : Int = 0,
     var core2 : Double = 0.0,
     var core2Unit : Conversion = Conversion.METERS,
     var core3 : Double = 0.0,
     var core3Unit : Conversion = Conversion.METERS,
     var core4 : Double = 0.0,
     var core4Unit : Conversion = Conversion.METERS,
     var core5 : Double = 0.0,
     var core5Unit : Conversion = Conversion.METERS,
     var core6 : Double = 0.0,
     var core6Unit : Conversion = Conversion.METERS,
)