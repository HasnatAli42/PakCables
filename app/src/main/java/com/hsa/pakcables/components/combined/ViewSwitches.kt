package com.hsa.pakcables.components.combined

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsa.pakcables.ui.theme.*

@Composable
fun ViewSwitches_2 (selectedSwitch : MutableState<Int>, labels : Array<String>){
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .clip(RoundedCornerShape(3.dp))
        ) {
            Text(text = labels[0],
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (selectedSwitch.value == 1) {
                    lightGreen
                } else {
                    colorOff
                },
                modifier = Modifier.fillMaxWidth(0.5f)
                    .clickable { selectedSwitch.value = 1 }
                    .background(color = GradientPurple)

            )
            Text(text = labels[1],
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = if (selectedSwitch.value == 2) {
                    lightGreen
                } else {
                    colorOff
                },
                modifier = Modifier.fillMaxWidth(1f)
                    .clickable { selectedSwitch.value = 2 }
                    .background(color = GradientBlue)
            )
        }
}