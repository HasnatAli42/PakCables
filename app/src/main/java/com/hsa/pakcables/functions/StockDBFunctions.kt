package com.hsa.pakcables.functions

import com.hsa.pakcables.database.tables.Stock
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