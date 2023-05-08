package com.hsa.pakcables.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.combined.HomeBottomBar
import com.hsa.pakcables.components.combined.HomeTopBar
import com.hsa.pakcables.components.singletons.OutPutContent
import com.hsa.pakcables.database.StockDataBase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(db : StockDataBase) {
    val selectedTab = remember { mutableStateOf(0) }
    val profileName = remember { mutableStateOf("Irfan") }
    val profileImage = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
                 HomeTopBar(profileName = profileName, profileImage = profileImage) {

                 }
        },
        bottomBar = {
            BottomNavigation(
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.fillMaxHeight(0.08f)
            ) {
                HomeBottomBar(
                    selectedTab = selectedTab
                )
            }
        }
    ) {
        when (selectedTab.value) {
            0 -> InputContent(db = db)
            1 -> OutPutContent(db = db)
            2 -> StockContent(db = db)
            3 -> CodingContent(db = db)
            4 -> ReportContent(db = db)
        }
    }
}




