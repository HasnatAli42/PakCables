package com.hsa.pakcables.components.combined

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.R
import com.hsa.pakcables.components.singletons.BottomBarTabsText
import com.hsa.pakcables.ui.theme.*

@Composable
fun HomeBottomBar(
    selectedTab : MutableState<Int>,
){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(gradientGrayBlack)
                .padding(all = 10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                BottomBarTabsIcon(
                    painterId = R.drawable.baseline_input_24,
                    selectedTab = selectedTab,
                    tabValue = 0)
                BottomBarTabsText(
                    text = inputText,
                    selectedTab = selectedTab,
                    tabValue = 0)
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                BottomBarTabsIcon(
                    painterId = R.drawable.baseline_menu_book_24,
                    selectedTab = selectedTab,
                    tabValue = 1
                )
                BottomBarTabsText(
                    text = outputText,
                    selectedTab = selectedTab,
                    tabValue = 1
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                BottomBarTabsIcon(
                    painterId = R.drawable.baseline_layers_24,
                    selectedTab = selectedTab,
                    tabValue = 2
                )
                BottomBarTabsText(
                    text = stockText,
                    selectedTab = selectedTab,
                    tabValue = 2
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                BottomBarTabsIcon(
                    painterId = R.drawable.outline_group_add_24,
                    selectedTab = selectedTab,
                    tabValue = 3
                )
                BottomBarTabsText(
                    text = codingText,
                    selectedTab = selectedTab,
                    tabValue = 3
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight(1f)
            ) {
                BottomBarTabsIcon(
                    painterId = R.drawable.baseline_report_24,
                    selectedTab = selectedTab,
                    tabValue = 4
                )
                BottomBarTabsText(
                    text = reportText,
                    selectedTab = selectedTab,
                    tabValue = 4
                )
            }
        }

    }

}

@Composable
fun BottomBarTabsIcon(painterId: Int, selectedTab : MutableState<Int>, tabValue: Int){
    IconButton(modifier = Modifier.size(30.dp), onClick = {
        if (selectedTab.value != tabValue) {
            selectedTab.value = tabValue
        }
    })
    {
        Icon(
            painter = painterResource(id = painterId)
            , contentDescription = "Home Button", tint = if (selectedTab.value == tabValue) { colorOn
            }else{
                colorOff
            }
        )
    }
}