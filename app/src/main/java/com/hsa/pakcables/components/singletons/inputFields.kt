package com.hsa.pakcables.components.singletons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hsa.pakcables.ui.theme.Red


@Composable
fun OutLinedInputWithError(stringMutableState: MutableState<String>, errorMutableState: MutableState<String>, labelText : String , keyboardType: KeyboardType, imeAction: ImeAction){
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = stringMutableState.value,
            onValueChange = { stringMutableState.value = it },
            label = { Text(text = labelText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            ),
            isError = errorMutableState.value.isNotEmpty(),
        )
        if (errorMutableState.value.isNotEmpty()){
            Text(text = errorMutableState.value, color = Red)
        }
    }
}

@Composable
fun PasswordOutLinedInputWithError(stringMutableState: MutableState<String>, errorMutableState: MutableState<String>, labelText : String , keyboardType: KeyboardType, imeAction: ImeAction){
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = stringMutableState.value,
            onValueChange = { stringMutableState.value = it },
            label = { Text(text = labelText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            ),
            isError = errorMutableState.value.isNotEmpty(),
        )
        if (errorMutableState.value.isNotEmpty()) {
            Text(text = errorMutableState.value, color = Red)
        }
    }
}

