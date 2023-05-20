package com.hsa.pakcables.components.cardViews

import com.hsa.pakcables.database.tables.Input
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.functions.viewDateFormat
import com.hsa.pakcables.ui.theme.*

@Composable
fun InputListView (input : Input, recordClicked: (selectedID : Int)-> Unit) {
    val bgColor = LightGreenCard
    val tintColor = GradientBlue

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = bgColor, shape = RoundedCornerShape(15.dp))
            .clickable { recordClicked(input.inputID) },
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
                Row(modifier = Modifier.fillMaxWidth(0.5f), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = input.remarks, color = lightGreen)
                }
                Row(modifier = Modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = viewDateFormat(input.lastUpdated), color = lightGreen)
                }
            }
        }

    }
}