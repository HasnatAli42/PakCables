package com.hsa.pakcables

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.hsa.pakcables.activities.HomeActivity
import com.hsa.pakcables.activities.LoginActivity
import com.hsa.pakcables.components.SplashScreen
import com.hsa.pakcables.components.singletons.textToast
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.functions.executeAfterDelay
import com.hsa.pakcables.ui.theme.PakCablesTheme
import com.hsa.pakcables.ui.theme.welcomeText

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            StockDataBase::class.java,
            "stock.db"
        ).fallbackToDestructiveMigration().build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PakCablesTheme {
                val context = LocalContext.current
                val currentUserCheck : List<CurrentUser> by db.currentUserDao.getUserById().collectAsState(
                    initial = emptyList()
                )
                if (currentUserCheck.isNotEmpty()){
                    if (currentUserCheck[0].isLoggedIn){
                        textToast(text = welcomeText + " " + currentUserCheck[0].firstName + " " + currentUserCheck[0].lastName, context = context)
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                }else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                }
                SplashScreen(onClick = {
                })
            }
        }
    }
}

