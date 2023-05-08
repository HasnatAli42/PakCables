package com.hsa.pakcables.components.combined

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.singletons.NormalOutLinedInputWithError
import com.hsa.pakcables.components.singletons.NormalPrimaryButton
import com.hsa.pakcables.ui.theme.*

@Composable
fun InputRowForItemCoding(
    stringMutableState : MutableState<String>,
    errorMutableState: MutableState<String>,
    buttonText: String,
    onSaveItemCoding : ()->Unit
){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth(1f)
        .padding(start = 10.dp, end = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            NormalOutLinedInputWithError(stringMutableState = stringMutableState, errorMutableState = errorMutableState, labelText = itemCodingInputText)
        }

        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            NormalPrimaryButton(event = { onSaveItemCoding() }, text = buttonText)
               }
    }

}

@Composable
fun InputRowForPartyCoding(
    stringMutableState : MutableState<String>,
    errorMutableState: MutableState<String>,
    buttonText: String,
    onSavePartyCoding : ()->Unit
){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 10.dp, end = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            NormalOutLinedInputWithError(stringMutableState = stringMutableState, errorMutableState = errorMutableState, labelText = partyCodingInputText)
        }

        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            NormalPrimaryButton(event = { onSavePartyCoding() }, text = buttonText)
        }
    }

}