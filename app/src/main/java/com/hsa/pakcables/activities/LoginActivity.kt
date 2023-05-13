package com.hsa.pakcables.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.hsa.pakcables.components.LoginScreen
import com.hsa.pakcables.components.singletons.textToast
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.functions.getCurrentDate
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class LoginActivity : ComponentActivity() {
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
            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }
            val isRemembered = remember { mutableStateOf(false) }

            val currentUserCheck : List<CurrentUser> by db.currentUserDao.getUserById().collectAsState(
                initial = emptyList()
            )
            if (currentUserCheck.isNotEmpty()){
                if(currentUserCheck[0].isRemembered) {
                    email.value = currentUserCheck[0].email
                    password.value = currentUserCheck[0].password
                    isRemembered.value = currentUserCheck[0].isRemembered
                }
            }
            val emailError = remember { mutableStateOf("") }
           val passwordError = remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            PakCablesTheme {
                LoginScreen(
                    email = email,
                    emailError = emailError,
                    password = password,
                    passwordError = passwordError,
                    isRemembered = isRemembered,
                    login = {
                        if (isLoginValid(
                                email = email,
                                emailError = emailError,
                                password = password,
                                passwordError = passwordError,
                        )){
                            lifecycleScope.launch {
                                return@launch withContext(Dispatchers.IO){
                                    val user = db.userDao.getUserByUsernameAndPassword(username = email.value, password = password.value)
                                    if (user == null){
                                        passwordError.value = inValidLoginText
                                        emailError.value = inValidLoginText
                                    }else{
                                        passwordError.value = ""
                                        emailError.value = ""
                                        val currentUser = CurrentUser(
                                            firstName = user.firstName,
                                            lastName = user.lastName,
                                            userName = user.userName,
                                            phoneNumber = user.phoneNumber,
                                            email = user.email,
                                            password = user.password,
                                            dob = user.dob,
                                            isAdmin = user.isAdmin,
                                            hasAcceptedTerms = user.hasAcceptedTerms,
                                            isRemembered = isRemembered.value,
                                            isLoggedIn = true,
                                            userID = user.id ?: 0,
                                            createdDate = user.createdDate,
                                            lastUpdated = getCurrentDate(),
                                        )
                                        coroutineScope.launch {
                                            db.currentUserDao.upsertUser(currentUser)
                                        }
                                        runOnUiThread {
                                            textToast(text = welcomeText + " " + user.firstName + " "+ user.lastName, context = context)
                                        }
                                        context.startActivity(Intent(context, HomeActivity::class.java))
                                        finish()
                                    }
                                }
                            }
                        }
                },
                    goToSignUpPage = {
                        startActivity(Intent(this, SignUpActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}

fun isLoginValid(
    email: MutableState<String>,
    emailError: MutableState<String>,
    password : MutableState<String>,
    passwordError : MutableState<String>,
): Boolean{
    if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
        emailError.value = inValidUserNameText
        return false
    }else{
        emailError.value = ""
    }
    if (password.value.length < 8){
        passwordError.value = inValidPasswordText
        return false
    }else{
        passwordError.value = ""
    }
    return true
}
