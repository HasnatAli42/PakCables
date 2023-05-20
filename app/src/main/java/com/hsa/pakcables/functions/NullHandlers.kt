package com.hsa.pakcables.functions

import com.hsa.pakcables.components.singletons.Conversion
import com.hsa.pakcables.ui.theme.meterToFeetConversionConstant

fun nullIntegerHandler(integer : Int?) : Int{
    return integer ?: 0
}

fun updateBasedOnUnits(amount : Double, unit: Conversion): Double{
    return if (unit == Conversion.FEET) {
        val converted = amount / meterToFeetConversionConstant
        String.format("%.2f", converted).toDouble()
    }else{
        amount
    }

}