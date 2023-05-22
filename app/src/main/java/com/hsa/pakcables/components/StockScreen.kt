package com.hsa.pakcables.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.cardViews.AddInputView
import com.hsa.pakcables.components.cardViews.PricesUpdateView
import com.hsa.pakcables.components.cardViews.StockDetailView
import com.hsa.pakcables.components.cardViews.StockDetailViewRowTotal
import com.hsa.pakcables.components.combined.ViewSwitches_2
import com.hsa.pakcables.components.singletons.HeadingTextCenterBlack
import com.hsa.pakcables.components.singletons.NormalPrimaryButton
import com.hsa.pakcables.components.singletons.textToast
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.Prices
import com.hsa.pakcables.database.tables.Stock
import com.hsa.pakcables.functions.nullIntegerHandler
import com.hsa.pakcables.models.InputRequestModel
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.launch

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
            2 -> PricesContent(db = db, currentUser = currentUser)
        }
    }
}

@Composable
fun StockDetailContent(db: StockDataBase, currentUser: CurrentUser) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val totalStockValue = remember { mutableStateOf(0) }
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
        StockDetailViewRowTotal(
            name = totalStockValueText,
            price = totalStockValue.value,
            textColor = GradientBlue
        )
        if (stockData.isNotEmpty() && pricesData.isNotEmpty() && itemsData.isNotEmpty()) {
            itemsData.forEach { element ->
                val indexOfStock =
                    stockData.indexOfFirst { stock -> stock.itemCodingID == element.id }
                val indexOfPrices =
                    pricesData.indexOfFirst { prices -> prices.itemCodingID == element.id }
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
fun PricesContent(db: StockDataBase, currentUser: CurrentUser) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val initial = remember{ mutableStateOf(true)}
    val pricesData: List<Prices> by db.pricesDao.getPrices(currentUser.userID)
        .collectAsState(initial = emptyList())
    val itemsData: List<ItemCoding> by db.itemCodingDao.getItemCoding(currentUser.userID)
        .collectAsState(initial = emptyList())
    val updatedPrices = remember { mutableListOf<Prices>() }
    if (initial.value) {
        pricesData.forEach { element ->
            initial.value = false
            updatedPrices.add(element)
        }
    }

    fun onPricesSaved(){
        updatedPrices.forEach { element->
            coroutineScope.launch {
                db.pricesDao.upsertPrices(element)
            }
        }
        textToast(text = pricesUpdatedSuccessText, context = context)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.91f)
            .verticalScroll(scroll)
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (itemsData.isNotEmpty() && updatedPrices.isNotEmpty()){
            itemsData.forEach { element ->
                val indexOfPrices =
                    updatedPrices.indexOfFirst { prices -> prices.itemCodingID == element.id }
                if (indexOfPrices != -1){
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    PricesUpdateView(prices = updatedPrices[indexOfPrices], item = element)
                }
            }
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
        NormalPrimaryButton(event = { onPricesSaved() }, text = updateText)
    }

}