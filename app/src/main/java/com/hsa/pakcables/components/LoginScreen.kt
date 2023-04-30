package com.hsa.pakcables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.singletons.*
import com.hsa.pakcables.ui.theme.gradientGrayBlack
import com.hsa.pakcables.ui.theme.loginDescription
import com.hsa.pakcables.ui.theme.loginText
import com.hsa.pakcables.ui.theme.pakCables

@Composable
fun LoginScreen(login : ()-> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(gradientGrayBlack)
            .padding(start = 20.dp, end = 20.dp),
    ) {
        MainHeadingTextCenter(text = pakCables)
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MaterialTheme.colors.background)
                    .padding(all = 10.dp)
            ) {
                // App Logo
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    MainHeadingTextStart(text = loginText)
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(1f)) {
                    DescriptionTextStart(text = loginDescription)
                }
                Spacer(modifier = Modifier.height(32.dp))
                // Email Input
                NormalOutLinedInput(stringMutableState = email)
                Spacer(modifier = Modifier.height(16.dp))
                // Password Input
                PasswordOutLinedInput(passwordState = password)
                Spacer(modifier = Modifier.height(16.dp))
                // Login Button
                NormalPrimaryButton(event = { login() }, text = "Login")
                Spacer(modifier = Modifier.height(16.dp))
                // Signup Text
                Text(
                    text = "Don't have an account? Sign up",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.clickable(onClick = { /* handle sign up */ })
                )
            }
        }

    }

}