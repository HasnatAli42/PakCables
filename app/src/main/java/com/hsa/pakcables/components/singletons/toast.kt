package com.hsa.pakcables.components.singletons

import android.content.Context
import android.widget.Toast
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

fun textToast(text : String , context : Context){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

//@Composable
//suspend fun SnackBarToast(text : String , ){
//    val snackBarHostState = remember { SnackbarHostState() }
//    snackBarHostState.showSnackbar(
//        message = text,
//        duration = SnackbarDuration.Short
//    )
//}