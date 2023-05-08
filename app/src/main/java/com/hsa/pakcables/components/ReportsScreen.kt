package com.hsa.pakcables.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hsa.pakcables.components.singletons.HeadingTextCenterBlack
import com.hsa.pakcables.database.StockDataBase

@Composable
fun ReportContent(db : StockDataBase) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeadingTextCenterBlack(text = "Report Content")
    }
}
