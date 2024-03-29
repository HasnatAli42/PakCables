package com.hsa.pakcables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.combined.MainTopBar
import com.hsa.pakcables.components.singletons.*
import com.hsa.pakcables.ui.theme.*

@Composable
fun LoginScreen(
    email: MutableState<String>,
    emailError: MutableState<String>,
    password : MutableState<String>,
    passwordError : MutableState<String>,
    isRemembered : MutableState<Boolean>,
    login : ()-> Unit,
    goToSignUpPage : ()-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(gradientGrayBlack)
    ) {
        MainTopBar ()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(gradientGrayBlack)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            Card(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .background(MaterialTheme.colors.background)
                        .padding(start = 10.dp, top = 50.dp, bottom = 50.dp, end = 10.dp)
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
                    OutLinedInputWithError(stringMutableState = email, labelText = userNameText , errorMutableState = emailError, keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    // Password Input
                    PasswordOutLinedInputWithError(stringMutableState = password, labelText = passwordText, errorMutableState = passwordError, keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
                    Spacer(modifier = Modifier.height(16.dp))
                    CheckboxComponent(isChecked = isRemembered, label = rememberMeText)
                    Spacer(modifier = Modifier.height(16.dp))
                    // Login Button
                    NormalPrimaryButton(event = { login() }, text = loginText)
                    Spacer(modifier = Modifier.height(16.dp))
                    // Signup Text
                    ClickableDescriptionTextStart(text = loginSignUpText) {
                        goToSignUpPage()
                    }
                }
            }
        }
    }
}