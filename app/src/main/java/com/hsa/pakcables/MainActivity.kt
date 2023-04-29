package com.hsa.pakcables

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hsa.pakcables.activities.LoginActivity
import com.hsa.pakcables.components.SplashScreen
import com.hsa.pakcables.functions.executeAfterDelay
import com.hsa.pakcables.ui.theme.PakCablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PakCablesTheme {
//                executeAfterDelay(1000) {
//                    startActivity(Intent(this, LoginActivity::class.java))
//                    finish()
//                }
                SplashScreen(onClick = {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                })
            }
        }
    }
}

