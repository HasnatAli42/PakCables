package com.hsa.pakcables.components

import androidx.compose.foundation.*
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
fun SignUpScreen(
    firstName : MutableState<String>,
    firstNameError : MutableState<String>,
    lastName : MutableState<String>,
    lastNameError : MutableState<String>,
    email : MutableState<String>,
    emailError : MutableState<String>,
    phoneNumber : MutableState<String>,
    phoneNumberError : MutableState<String>,
    password : MutableState<String>,
    passwordError : MutableState<String>,
    confirmPassword : MutableState<String>,
    confirmPasswordError : MutableState<String>,
    code : MutableState<String>,
    codeError : MutableState<String>,
    signUp : ()-> Unit,
    goToLogin : ()-> Unit,
) {
    val scroll = rememberScrollState()
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
                        .fillMaxHeight(1f)
                        .background(MaterialTheme.colors.background)
                        .padding(start = 10.dp, top = 50.dp, bottom = 50.dp, end = 10.dp)
                        .verticalScroll(scroll)
                ) {
                    // App Logo
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    ) {
                        MainHeadingTextStart(text = signUpText)
                    }
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth(1f)) {
                        DescriptionTextStart(text = signupDescription)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    OutLinedInputWithError(stringMutableState = firstName, errorMutableState = firstNameError, labelText = firstNameText, keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutLinedInputWithError(stringMutableState = lastName, errorMutableState = lastNameError, labelText = lastNameText, keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutLinedInputWithError(stringMutableState = email, errorMutableState = emailError, labelText = emailText, keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutLinedInputWithError(stringMutableState = phoneNumber, errorMutableState = phoneNumberError, labelText = phoneNumberText, keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    PasswordOutLinedInputWithError(stringMutableState = password, errorMutableState = passwordError, labelText = passwordText, keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    PasswordOutLinedInputWithError(stringMutableState = confirmPassword, errorMutableState = confirmPasswordError, labelText = confirmPasswordText, keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutLinedInputWithError(stringMutableState = code, errorMutableState = codeError, labelText = codeText, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
                    Spacer(modifier = Modifier.height(16.dp))
                    NormalPrimaryButton(event = { signUp() }, text = signUpText)
                    Spacer(modifier = Modifier.height(16.dp))
                    ClickableDescriptionTextStart(text = signUpLoginText) {
                        goToLogin()
                    }
                }
            }
        }
    }
}