package com.hsa.pakcables.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.cardViews.AddInputView
import com.hsa.pakcables.components.cardViews.StockDetailView
import com.hsa.pakcables.components.cardViews.StockDetailViewRowTotal
import com.hsa.pakcables.components.combined.ViewSwitches_2
import com.hsa.pakcables.components.singletons.HeadingTextCenterBlack
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.Prices
import com.hsa.pakcables.database.tables.Stock
import com.hsa.pakcables.ui.theme.*

@Composable
fun StockContent(db: StockDataBase, currentUser: CurrentUser) {
    val selectedSwitch = remember { mutableStateOf(1) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.92f)
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ViewSwitches_2(
            selectedSwitch = selectedSwitch,
            labels = arrayOf(stockText, pricingText)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        when (selectedSwitch.value) {
            1 -> StockDetailContent(db = db, currentUser = currentUser)
            2 -> PricesContent(db = db)
        }
    }
}

@Composable
fun StockDetailContent(db: StockDataBase, currentUser: CurrentUser) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val totalStockValue = remember { mutableStateOf(0)}
    val stockData: List<Stock> by db.stockDao.getStock(currentUser.userID)
        .collectAsState(initial = emptyList())
    val pricesData: List<Prices> by db.pricesDao.getPrices(currentUser.userID)
        .collectAsState(initial = emptyList())
    val itemsData: List<ItemCoding> by db.itemCodingDao.getItemCoding(currentUser.userID)
        .collectAsState(initial = emptyList())



     Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scroll)
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         StockDetailViewRowTotal(name = totalStockValueText, price = totalStockValue.value, textColor = GradientBlue)
        if (stockData.isNotEmpty() && pricesData.isNotEmpty() && itemsData.isNotEmpty()) {
            itemsData.forEach { element ->
                val indexOfStock =
                    stockData.indexOfFirst { stock -> stock.itemCodingID == element.id }
                val indexOfPrices =
                    stockData.indexOfFirst { prices -> prices.itemCodingID == element.id }
                if (indexOfStock != -1 && indexOfPrices != -1) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    StockDetailView(
                        stock = stockData[indexOfStock],
                        prices = pricesData[indexOfPrices],
                        item = element,
                        totalValue = totalStockValue
                    )
                }
            }
        }
    }

}

@Composable
fun PricesContent(db: StockDataBase) {

}