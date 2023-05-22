package com.hsa.pakcables.components.cardViews

import com.hsa.pakcables.database.tables.Input
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.hsa.pakcables.components.singletons.Conversion
import com.hsa.pakcables.components.singletons.MeasurementConverterOutLineTextField
import com.hsa.pakcables.components.singletons.OutLineTextFieldWithIntegerInput
import com.hsa.pakcables.components.singletons.OutLineTextFieldWithIntegerTotalInput
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.Prices
import com.hsa.pakcables.functions.viewDateFormat
import com.hsa.pakcables.ui.theme.*

@Composable
fun PricesUpdateView(prices: Prices, item : ItemCoding) {
    val bgColor = lightGreen
    val textColor = GradientBlue
    val rollPrice = remember{ mutableStateOf(prices.red.toString())}
    val corePrice = remember{ mutableStateOf(prices.core2.toString())}

    if (rollPrice.value.isNotEmpty()){
         prices.red = rollPrice.value.toInt()
         prices.black = rollPrice.value.toInt()
         prices.yellow = rollPrice.value.toInt()
         prices.blue = rollPrice.value.toInt()
         prices.green = rollPrice.value.toInt()
         prices.white = rollPrice.value.toInt()
         prices.other = rollPrice.value.toInt()
         prices.total = rollPrice.value.toInt()

    }
    if (corePrice.value.isNotEmpty()){
        prices.core2 = corePrice.value.toInt()
        prices.core3 = corePrice.value.toInt()
        prices.core4 = corePrice.value.toInt()
        prices.core5 = corePrice.value.toInt()
        prices.core6 = corePrice.value.toInt()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = bgColor, shape = RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = bgColor)
                .padding(all = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = GradientBlue, shape = RoundedCornerShape(15.dp))
                        .padding(all = 5.dp),
                ) {
                    Text(
                        text = item.name, color = Color.White,
                        modifier = Modifier
                            .background(GradientBlue)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.45f)
                ) {
                    OutLineTextFieldWithIntegerInput(labelText = rollPriceText, integerInputState = rollPrice
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(0.82f)
                ) {
                    OutLineTextFieldWithIntegerInput(labelText = corePriceText, integerInputState = corePrice
                    )
                }
            }
        }

    }
}