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
import com.hsa.pakcables.functions.viewDateFormat
import com.hsa.pakcables.ui.theme.*

@Composable
fun InputDetailView(input: Input) {
    val bgColor = YellowCard
    val textColor = lightGreen

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
                        text = input.itemName, color = Color.White,
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
                Text(text = redText + ":" + input.red.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.3f))
                Text(text = blackText + ":" + input.black.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.5f))
                Text(text = blueText + ":" + input.blue.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.75f))

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = greenText + ":" + input.green.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.3f))
                Text(text = yellowText + ":" + input.yellow.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.5f))
                Text(text = whiteText + ":" + input.white.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.75f))


            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = otherText + ":" + input.other.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.3f))
                Text(text = totalText + ":" + input.total.toString(), color = textColor, modifier = Modifier.fillMaxWidth(0.5f))
                Text(text = core2Text + ":" + input.core2.toString() + meterText, color = textColor, modifier = Modifier.fillMaxWidth(0.75f))
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = core3Text + ":" + input.core3.toString() + meterText, color = textColor, modifier = Modifier.fillMaxWidth(0.3f))
                Text(text = core4Text + ":" + input.core4.toString() + meterText, color = textColor, modifier = Modifier.fillMaxWidth(0.5f))
                Text(text = core5Text + ":" + input.core5.toString() + meterText, color = textColor, modifier = Modifier.fillMaxWidth(0.75f))
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = core6Text + ":" + input.core6.toString() + meterText, color = textColor, modifier = Modifier.fillMaxWidth())
            }
        }

    }
}