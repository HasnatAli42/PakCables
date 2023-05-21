package com.hsa.pakcables.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.combined.ViewSwitches_2
import com.hsa.pakcables.components.singletons.HeadingTextCenterBlack
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.ui.theme.itemCodingText
import com.hsa.pakcables.ui.theme.partyCodingText
import com.hsa.pakcables.ui.theme.pricingText
import com.hsa.pakcables.ui.theme.stockText

@Composable
fun StockContent(db : StockDataBase, currentUser : CurrentUser) {
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
            1 -> StockDetailContent(db = db)
            2 -> PricesContent(db = db)
        }
    }
}

@Composable
fun StockDetailContent (db : StockDataBase){


}

@Composable
fun PricesContent (db : StockDataBase){

}