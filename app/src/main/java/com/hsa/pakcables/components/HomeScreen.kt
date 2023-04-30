package com.hsa.pakcables.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My App") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                elevation = 4.dp
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 4.dp
            ) {
                val tabs = listOf(
                    Pair(Icons.Default.Home, "Home"),
                    Pair(Icons.Default.Search, "Search"),
                    Pair(Icons.Default.Favorite, "Favorites")
                )
                tabs.forEachIndexed { index, tab ->
                    val selected = selectedTab == index
                    val tint = if (selected) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    }
                    Tab(
                        selected = selected,
                        onClick = { selectedTab = index },
                        content = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = tab.first,
                                    contentDescription = tab.second,
                                    tint = tint
                                )
                                Text(
                                    text = tab.second,
                                    color = tint
                                )
                            }
                        }
                    )
                }
            }
        }
    ) {
        // Show content based on selected tab
        when (selectedTab) {
            0 -> HomeContent()
            1 -> SearchContent()
            2 -> FavoritesContent()
        }
    }
}

@Composable
fun HomeContent() {
    // TODO: Add content for Home tab
}

@Composable
fun SearchContent() {
    // TODO: Add content for Search tab
}

@Composable
fun FavoritesContent() {
    // TODO: Add content for Favorites tab
}