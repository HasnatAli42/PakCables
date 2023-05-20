package com.hsa.pakcables.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hsa.pakcables.components.singletons.Conversion

class InputRequestModel {
     val itemName : String = ""
     val itemID : Int = 0
     val red : Int = 0
     val black : Int = 0
     val yellow : Int = 0
     val blue : Int = 0
     val green : Int = 0
     val white : Int = 0
     val other : Int = 0
     val total : Int = 0
     val core2 : Double = 0.0
     val core2Unit : Conversion = Conversion.METERS
     val core3 : Double = 0.0
     val core3Unit : Conversion = Conversion.METERS
     val core4 : Double = 0.0
     val core4Unit : Conversion = Conversion.METERS
     val core5 : Double = 0.0
     val core5Unit : Conversion = Conversion.METERS
     val core6 : Double = 0.0
     val core6Unit : Conversion = Conversion.METERS
}