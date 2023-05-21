package com.hsa.pakcables.functions

import com.hsa.pakcables.database.tables.Stock
import com.hsa.pakcables.models.InputRequestModel
import com.hsa.pakcables.models.StockRequestModel

fun convertStockRequestToStock(request: StockRequestModel, userID: Int): Stock {
    return Stock(
        red = request.red,
        black = request.black,
        yellow = request.yellow,
        blue = request.blue,
        green = request.green,
        white = request.white,
        other = request.other,
        total = request.total,
        core2 = request.core2,
        core3 = request.core3,
        core4 = request.core4,
        core5 = request.core5,
        core6 = request.core6,
        createdDate = request.createdDate,
        lastUpdated = request.lastUpdated,
        itemName = request.itemName,
        itemCodingID = request.itemCodingID,
        userID = userID,
        id = request.id,
    )
}

fun addInputToStock(input : InputRequestModel, previousStock: Stock, userID: Int ) : Stock {
    return Stock(
        red = input.red + previousStock.red,
        black = input.black + previousStock.black,
        yellow = input.yellow + previousStock.yellow,
        blue = input.blue + previousStock.blue,
        green = input.green + previousStock.green,
        white = input.white + previousStock.white,
        other = input.other + previousStock.other,
        total = input.total + previousStock.total,
        core2 = if(input.core2 != 0.0){previousStock.core2.plus(input.core2)}else{previousStock.core2},
        core3 = if(input.core3 != 0.0){previousStock.core2.plus(input.core3)}else{previousStock.core3},
        core4 = if(input.core4 != 0.0){previousStock.core2.plus(input.core4)}else{previousStock.core4},
        core5 = if(input.core5 != 0.0){previousStock.core2.plus(input.core5)}else{previousStock.core5},
        core6 = if(input.core6 != 0.0){previousStock.core2.plus(input.core6)}else{previousStock.core6},
        createdDate = previousStock.createdDate,
        lastUpdated = getCurrentDate(),
        itemName = previousStock.itemName,
        itemCodingID = previousStock.itemCodingID,
        userID = userID,
        id = previousStock.id,
    )

}