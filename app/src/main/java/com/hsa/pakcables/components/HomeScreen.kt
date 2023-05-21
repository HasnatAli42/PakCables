package com.hsa.pakcables.components

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.activities.LoginActivity
import com.hsa.pakcables.components.combined.HomeBottomBar
import com.hsa.pakcables.components.combined.HomeTopBar
import com.hsa.pakcables.components.singletons.OutPutContent
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(db : StockDataBase , logOutCalled : ()-> Unit) {
    val currentUserCheck : List<CurrentUser> by db.currentUserDao.getUserById().collectAsState(
        initial = emptyList()
    )
    val selectedTab = remember { mutableStateOf(0) }
    val profileName = remember { mutableStateOf("") }
    val profileImage = remember { mutableStateOf("") }

    if (currentUserCheck.isNotEmpty()){
        profileName.value = currentUserCheck[0].firstName + " " + currentUserCheck[0].lastName
    }

    Scaffold(
        topBar = {
                 HomeTopBar(
                     profileName = profileName,
                     profileImage = profileImage ,
                     profilePicClicked = {},
                     logOutCalled = {
                         logOutCalled()
                     }
                 )
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
        if (currentUserCheck.isNotEmpty()) {
            when (selectedTab.value) {
                0 -> InputContent(db = db, currentUser = currentUserCheck[0])
                1 -> OutPutContent(db = db)
                2 -> StockContent(db = db, currentUser = currentUserCheck[0])
                3 -> CodingContent(db = db)
            }
        }
    }
}




