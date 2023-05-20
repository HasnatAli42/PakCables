package com.hsa.pakcables.components.singletons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.R
import com.hsa.pakcables.ui.theme.*


@Composable
fun OutLinedInputWithError(stringMutableState: MutableState<String>, errorMutableState: MutableState<String>, labelText : String , keyboardType: KeyboardType, imeAction: ImeAction){
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

@Composable
fun MeasurementConverterOutLineTextField(labelText : String) {
    val inputValue = remember { mutableStateOf("") }
    val selectedConversion = remember { mutableStateOf(Conversion.METERS) }
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                if (it.isEmpty()){
                    inputValue.value = it
                } else {
                    inputValue.value = when (it.toDoubleOrNull()) {
                        null -> inputValue.value //old value
                        else -> it   //new value
                    }
                }
                            },
            label = { Text(text = labelText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            ),
            trailingIcon = {
                if (selectedConversion.value == Conversion.METERS){
                    Button(
                        onClick = {
                            if (inputValue.value.isNotEmpty()){
                                val meters = inputValue.value
                                val feet = meters.toDoubleOrNull()?.times((3.28084))
                                inputValue.value = String.format("%.2f", feet)
                            }
                            selectedConversion.value = Conversion.FEET
                                  },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary // Use primary color for selected conversion
                        ),
                        modifier = Modifier.padding(end = 5.dp)
                    ) {
                        Text(meterText)
                    }
                }else{
                    Button(
                        onClick = {
                            if (inputValue.value.isNotEmpty()) {
                                val feet = inputValue.value
                                val meters = feet.toDoubleOrNull()?.div((3.28084))
                                inputValue.value = String.format("%.2f", meters)
                            }
                            selectedConversion.value = Conversion.METERS
                                  },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier.padding(end = 5.dp)
                    ){
                        Text(feetText)
                    }
                }
            }

        )

    }
}

@Composable
fun OutLineTextFieldWithIntegerInput(labelText : String) {
    val inputValue = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                if (it.isEmpty()){
                    inputValue.value = it
                } else {
                    inputValue.value = when (it.toIntOrNull()) {
                        null -> inputValue.value //old value
                        else -> it   //new value
                    }
                }
            },
            label = { Text(text = labelText) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            ),
        )
    }
}

@Composable
fun OutLinedInputWithSearch(stringMutableState: MutableState<String>, labelText : String , keyboardType: KeyboardType, imeAction: ImeAction){
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
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.onSurface
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            trailingIcon = { Icon(painter =painterResource(id = R.drawable.baseline_search_24), contentDescription = searchText, tint = lightGreen) }
        )
    }
}

enum class Conversion {
    METERS,
    FEET
}
