package com.hsa.pakcables.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.combined.HomeBottomBar
import com.hsa.pakcables.components.combined.HomeTopBar
import com.hsa.pakcables.components.singletons.HeadingTextCenterBlack
import com.hsa.pakcables.components.singletons.HeadingTextCenterWhite

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val selectedTab = remember { mutableStateOf(0) }
    val profileName = remember { mutableStateOf("Hasnat Ali") }
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
            0 -> InputContent()
            1 -> OutPutContent()
            2 -> PartyContent()
            3 -> StockContent()
            4 -> ReportContent()
        }
    }
}

@Composable
fun InputContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeadingTextCenterBlack(text = "Input Content")
    }
}

@Composable
fun OutPutContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeadingTextCenterBlack(text = "Output Content")
    }
}

@Composable
fun ReportContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeadingTextCenterBlack(text = "Report Content")
    }
}

@Composable
fun StockContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeadingTextCenterBlack(text = "Stock Content")
    }
}

@Composable
fun PartyContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeadingTextCenterBlack(text = "Party Content")
    }
}