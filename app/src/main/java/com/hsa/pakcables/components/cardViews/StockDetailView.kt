package com.hsa.pakcables.components.cardViews

import com.hsa.pakcables.database.tables.Input
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.hsa.pakcables.components.singletons.Conversion
import com.hsa.pakcables.components.singletons.MeasurementConverterOutLineTextField
import com.hsa.pakcables.components.singletons.OutLineTextFieldWithIntegerInput
import com.hsa.pakcables.components.singletons.OutLineTextFieldWithIntegerTotalInput
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.Prices
import com.hsa.pakcables.database.tables.Stock
import com.hsa.pakcables.functions.viewDateFormat
import com.hsa.pakcables.ui.theme.*

@Composable
fun StockDetailView(stock: Stock, prices: Prices, item: ItemCoding, totalValue: MutableState<Int>) {
    val bgColor = lightGreen
    val textColor = GradientBlue
    val firstFloat = 0.3f
    val secondFloat = 0.5f
    val thirdFloat = 0.9f
    val total = remember { mutableStateOf(sumStock(stock = stock, price = prices)) }
    val initial = remember { mutableStateOf(true) }
    if (initial.value){
        initial.value = false
        totalValue.value = totalValue.value + total.value
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = nameText, color = textColor, fontWeight = FontWeight.Bold ,modifier = Modifier.fillMaxWidth(firstFloat))
                Text(text = quantityText, color = textColor, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(secondFloat))
                Text(text = priceText, color = textColor, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(thirdFloat))
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            StockDetailViewRow(name = redText, quantity = stock.red, price = prices.red, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = blackText, quantity = stock.black, price = prices.black, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = blueText, quantity = stock.blue, price = prices.blue, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = greenText, quantity = stock.green, price = prices.green, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = yellowText, quantity = stock.yellow, price = prices.yellow, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = whiteText, quantity = stock.white, price = prices.white, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = otherText, quantity = stock.other, price = prices.other, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRow(name = totalText, quantity = stock.total, price = prices.total, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRowCore(name = core2Text, quantity = stock.core2.sum(), price = prices.core2, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRowCore(name = core3Text, quantity = stock.core3.sum(), price = prices.core3, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRowCore(name = core4Text, quantity = stock.core4.sum(), price = prices.core4, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRowCore(name = core5Text, quantity = stock.core5.sum(), price = prices.core5, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRowCore(name = core6Text, quantity = stock.core6.sum(), price = prices.core6, firstFloat = firstFloat, secondFloat = secondFloat, thirdFloat = thirdFloat, textColor = GradientBlue   )
            StockDetailViewRowTotal(name = totalText, price = total.value, textColor = GradientBlue   )
        }

    }
}

@Composable
fun StockDetailViewRow(name: String, quantity: Int, price : Int, firstFloat: Float, secondFloat: Float, thirdFloat: Float , textColor: Color){
    if (quantity != 0){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = name, color = textColor, modifier = Modifier.fillMaxWidth(firstFloat))
        Text(text = quantity.toString(), color = textColor, modifier = Modifier.fillMaxWidth(secondFloat))
        Text(text = "Rs.${quantity * price}", color = textColor, modifier = Modifier.fillMaxWidth(thirdFloat))
    }
    }
}

@Composable
fun StockDetailViewRowCore(name: String, quantity: Double, price : Int, firstFloat: Float, secondFloat: Float, thirdFloat: Float , textColor: Color){
    if (quantity != 0.0){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = name, color = textColor, modifier = Modifier.fillMaxWidth(firstFloat))
        Text(text = quantity.toString(), color = textColor, modifier = Modifier.fillMaxWidth(secondFloat))
        Text(text = "Rs.${(quantity * price).toInt()}", color = textColor, modifier = Modifier.fillMaxWidth(thirdFloat))
    }
    }
}

@Composable
fun StockDetailViewRowTotal(name: String, price : Int, textColor: Color){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "${name}: Rs.${price}", color = textColor, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
    }
}

fun sumStock (stock : Stock, price : Prices): Int{
    return ((stock.red * price.red)+(stock.black * price.black)
            +(stock.blue * price.blue)+(stock.yellow * price.yellow)
            +(stock.green * price.green)+(stock.white * price.white)
            +(stock.other * price.other)+(stock.total * price.total)
            +(stock.core2.sum().toInt() * price.core2)+(stock.core3.sum().toInt() * price.core3)
            +(stock.core4.sum().toInt() * price.core4)+(stock.core4.sum().toInt() * price.core4)
            +(stock.core5.sum().toInt() * price.core5))
}