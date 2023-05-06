package com.hsa.pakcables.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.room.Room
import com.hsa.pakcables.components.HomeScreen
import com.hsa.pakcables.components.LoginScreen
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.ui.theme.PakCablesTheme
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            StockDataBase::class.java,
            "stock.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PakCablesTheme {
                HomeScreen(db = db)
            }
        }
    }
}
