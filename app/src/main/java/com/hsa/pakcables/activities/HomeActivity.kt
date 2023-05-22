package com.hsa.pakcables.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.hsa.pakcables.components.HomeScreen
import com.hsa.pakcables.components.LoginScreen
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.ui.theme.PakCablesTheme
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            StockDataBase::class.java,
            "stock.db"
        ).fallbackToDestructiveMigration().build()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PakCablesTheme {
                val currentUserCheck : List<CurrentUser> by db.currentUserDao.getUserById().collectAsState(
                    initial = emptyList()
                )
                val coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current

                HomeScreen(db = db , logOutCalled = {
                    if (currentUserCheck.isNotEmpty()){
                        coroutineScope.launch {
                            db.currentUserDao.upsertUser(currentUserCheck[0].copy(isLoggedIn = false))
                        }
                        context.startActivity(Intent(context, LoginActivity::class.java))
                        finish()
                    }
                })
            }
        }
    }
}
