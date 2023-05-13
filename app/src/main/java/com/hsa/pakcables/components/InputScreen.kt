package com.hsa.pakcables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.R
import com.hsa.pakcables.components.singletons.HeadingTextCenterBlack
import com.hsa.pakcables.components.singletons.SymbolGradientCircleButton
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.functions.getCurrentDate
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun InputContent(db : StockDataBase) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        SymbolGradientCircleButton(event = {}, icon = painterResource(id = R.drawable.baseline_assignment_24))
        HeadingTextCenterBlack(text = "Input Content")

    }
}