package com.hsa.pakcables.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.hsa.pakcables.components.LoginScreen
import com.hsa.pakcables.components.SignUpScreen
import com.hsa.pakcables.components.combined.MainTopBar
import com.hsa.pakcables.components.singletons.textToast
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.User
import com.hsa.pakcables.functions.getCurrentDate
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import java.util.regex.Pattern

class SignUpActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            StockDataBase::class.java,
            "stock.db"
        ).fallbackToDestructiveMigration().build()
    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val firstName = remember { mutableStateOf("") }
            val firstNameError = remember { mutableStateOf("") }
            val lastName = remember { mutableStateOf("") }
            val lastNameError = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            val emailError = remember { mutableStateOf("") }
            val phoneNumber = remember { mutableStateOf("") }
            val phoneNumberError = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val passwordError = remember { mutableStateOf("") }
            val confirmPassword = remember { mutableStateOf("") }
            val confirmPasswordError = remember { mutableStateOf("") }
            val code = remember { mutableStateOf("") }
            val codeError = remember { mutableStateOf("") }
            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            PakCablesTheme {

                SignUpScreen(
                    firstName = firstName,
                    firstNameError = firstNameError,
                    lastName = lastName,
                    lastNameError = lastNameError,
                    email = email,
                    emailError = emailError,
                    phoneNumber = phoneNumber,
                    phoneNumberError = phoneNumberError,
                    password = password,
                    passwordError = passwordError,
                    confirmPassword = confirmPassword,
                    confirmPasswordError = confirmPasswordError,
                    code = code,
                    codeError = codeError,
                    signUp = {
                        if (isSignupValid(
                                firstName = firstName,
                                firstNameError = firstNameError,
                                lastName = lastName,
                                lastNameError = lastNameError,
                                email = email,
                                emailError = emailError,
                                phoneNumber = phoneNumber,
                                phoneNumberError = phoneNumberError,
                                password = password,
                                passwordError = passwordError,
                                confirmPassword = confirmPassword,
                                confirmPasswordError = confirmPasswordError,
                                code = code,
                                codeError = codeError,
                        )){
                            val user = User(
                                firstName = firstName.value,
                            lastName = lastName.value,
                            userName = email.value,
                            phoneNumber = phoneNumber.value,
                            email = email.value,
                            dob = "",
                            password = password.value,
                            isAdmin = false,
                            hasAcceptedTerms = true,
                            createdDate = getCurrentDate(), lastUpdated = getCurrentDate(),
                            )
                            coroutineScope.launch {
                                db.userDao.upsertUser(user = user)
                            }
                            textToast(text = signUpSuccessText, context = context)
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    },
                    goToLogin = {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                )



            }
        }
    }
}

fun isSignupValid(
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
) : Boolean{
    if (firstName.value.isEmpty()){
        firstNameError.value = fieldRequiredText
        return false
    }else{
        firstNameError.value = ""
    }
    if (lastName.value.isEmpty()){
        lastNameError.value = fieldRequiredText
        return false
    }else{
        lastNameError.value = ""
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
        emailError.value = inValidEmailText
        return false
    }else{
        emailError.value = ""
    }
    if (phoneNumber.value.isEmpty()){
        phoneNumberError.value = fieldRequiredText
        return false
    }else{
        phoneNumberError.value = ""
    }
    if (password.value.length < 8){
        passwordError.value = weakPasswordText
        return false
    }else{
        passwordError.value = ""
    }
    if (password.value != confirmPassword.value){
        passwordError.value = unMatchedPasswordsText
        confirmPasswordError.value = unMatchedPasswordsText
        return false
    }else{
        passwordError.value = ""
        confirmPasswordError.value = ""
    }
    if (code.value != pakCablesCodeText){
        codeError.value = invalidCodeText
        return false
    }else{
        codeError.value = ""
    }
    return true
}


