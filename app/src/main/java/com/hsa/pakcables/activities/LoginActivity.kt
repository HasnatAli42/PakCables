package com.hsa.pakcables.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hsa.pakcables.components.LoginScreen
import com.hsa.pakcables.ui.theme.PakCablesTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PakCablesTheme {
                LoginScreen {

                }
            }
        }
    }
}
