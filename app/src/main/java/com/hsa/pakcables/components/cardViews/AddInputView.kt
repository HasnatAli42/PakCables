package com.hsa.pakcables.components.cardViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.hsa.pakcables.components.singletons.Conversion
import com.hsa.pakcables.components.singletons.MeasurementConverterOutLineTextField
import com.hsa.pakcables.components.singletons.OutLineTextFieldWithIntegerInput
import com.hsa.pakcables.components.singletons.OutLineTextFieldWithIntegerTotalInput
import com.hsa.pakcables.models.InputRequestModel
import com.hsa.pakcables.ui.theme.*

@Composable
fun AddInputView(input: InputRequestModel) {
    val bgColor = lightGreen
    val tintColor = GradientBlue
    val redInput = remember { mutableStateOf("") }
    val blackInput = remember { mutableStateOf("") }
    val blueInput = remember { mutableStateOf("") }
    val greenInput = remember { mutableStateOf("") }
    val yellowInput = remember { mutableStateOf("") }
    val whiteInput = remember { mutableStateOf("") }
    val otherInput = remember { mutableStateOf("") }
    val totalInput = remember { mutableStateOf("") }
    val core2Input = remember { mutableStateOf("") }
    val core2InputUnit = remember { mutableStateOf(Conversion.METERS) }
    val core3Input = remember { mutableStateOf("") }
    val core3InputUnit = remember { mutableStateOf(Conversion.METERS) }
    val core4Input = remember { mutableStateOf("") }
    val core4InputUnit = remember { mutableStateOf(Conversion.METERS) }
    val core5Input = remember { mutableStateOf("") }
    val core5InputUnit = remember { mutableStateOf(Conversion.METERS) }
    val core6Input = remember { mutableStateOf("") }
    val core6InputUnit = remember { mutableStateOf(Conversion.METERS) }
    if (redInput.value.isDigitsOnly()) {
        input.red = when (redInput.value.toIntOrNull()) {
            null -> 0
            else -> redInput.value.toInt()
        }
        totalInput.value =
            (input.red + input.black + input.blue + input.green + input.yellow + input.white + input.other).toString()
    }
    if (blackInput.value.isDigitsOnly()) {
        input.black = when (blackInput.value.toIntOrNull()) {
            null -> 0
            else -> blackInput.value.toInt()
        }
        totalInput.value =
            (input.red + input.black + input.blue + input.green + input.yellow + input.white + input.other).toString()
    }
    if (blueInput.value.isDigitsOnly()) {
        input.blue = when (blueInput.value.toIntOrNull()) {
            null -> 0
            else -> blueInput.value.toInt()
        }
        totalInput.value =
            (input.red + input.black + input.blue + input.green + input.yellow + input.white + input.other).toString()
    }
    if (greenInput.value.isDigitsOnly()) {
        input.green = when (greenInput.value.toIntOrNull()) {
            null -> 0
            else -> greenInput.value.toInt()
        }
        totalInput.value =
            (input.red + input.black + input.blue + input.green + input.yellow + input.white + input.other).toString()
    }
    if (yellowInput.value.isDigitsOnly()) {
        input.yellow = when (yellowInput.value.toIntOrNull()) {
            null -> 0
            else -> yellowInput.value.toInt()
        }
    }
    if (whiteInput.value.isDigitsOnly()) {
        input.white = when (whiteInput.value.toIntOrNull()) {
            null -> 0
            else -> whiteInput.value.toInt()
        }
        totalInput.value =
            (input.red + input.black + input.blue + input.green + input.yellow + input.white + input.other).toString()
    }
    if (otherInput.value.isDigitsOnly()) {
        input.other = when (otherInput.value.toIntOrNull()) {
            null -> 0
            else -> otherInput.value.toInt()
        }
        totalInput.value =
            (input.red + input.black + input.blue + input.green + input.yellow + input.white + input.other).toString()
    }
    if (totalInput.value.isDigitsOnly()) {
        input.total = when (totalInput.value.toIntOrNull()) {
            null -> 0
            else -> totalInput.value.toInt()
        }
    }
    if (core2Input.value.isDigitsOnly()) {
        input.core2 = when (core2Input.value.toDoubleOrNull()) {
            null -> 0.0
            else -> core2Input.value.toDouble()
        }
        input.core2Unit = core2InputUnit.value
    }
    if (core3Input.value.isDigitsOnly()) {
        input.core3 = when (core3Input.value.toDoubleOrNull()) {
            null -> 0.0
            else -> core3Input.value.toDouble()
        }
        input.core3Unit = core3InputUnit.value
    }
    if (core4Input.value.isDigitsOnly()) {
        input.core4 = when (core4Input.value.toDoubleOrNull()) {
            null -> 0.0
            else -> core4Input.value.toDouble()
        }
        input.core4Unit = core4InputUnit.value
    }
    if (core5Input.value.isDigitsOnly()) {
        input.core5 = when (core5Input.value.toDoubleOrNull()) {
            null -> 0.0
            else -> core5Input.value.toDouble()
        }
        input.core5Unit = core5InputUnit.value
    }
    if (core6Input.value.isDigitsOnly()) {
        input.core6 = when (core6Input.value.toDoubleOrNull()) {
            null -> 0.0
            else -> core6Input.value.toDouble()
        }
        input.core6Unit = core6InputUnit.value
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = bgColor, shape = RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = bgColor)
                .padding(all = 10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.27f)
                        .background(color = GradientBlue, shape = RoundedCornerShape(15.dp))
                        .padding(all = 5.dp),
                ) {
                    Text(
                        text = input.itemName, color = Color.White, modifier = Modifier
                            .background(GradientBlue),
                        textAlign = TextAlign.Center
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.35f)) {
                    OutLineTextFieldWithIntegerTotalInput(
                        labelText = totalText,
                        integerInputState = totalInput
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = otherText,
                        integerInputState = otherInput
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.27f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = redText,
                        integerInputState = redInput
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.35f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = blackText,
                        integerInputState = blackInput
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = blueText,
                        integerInputState = blueInput
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.27f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = greenText,
                        integerInputState = greenInput
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.35f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = yellowText,
                        integerInputState = yellowInput
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                    OutLineTextFieldWithIntegerInput(
                        labelText = whiteText,
                        integerInputState = whiteInput
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.45f)) {
                    MeasurementConverterOutLineTextField(
                        labelText = core2Text,
                        inputValue = core2Input,
                        selectedConversion = core2InputUnit
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.8f)) {
                    MeasurementConverterOutLineTextField(
                        labelText = core3Text,
                        inputValue = core3Input,
                        selectedConversion = core3InputUnit
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.45f)) {
                    MeasurementConverterOutLineTextField(
                        labelText = core4Text,
                        inputValue = core4Input,
                        selectedConversion = core4InputUnit
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(0.8f)) {
                    MeasurementConverterOutLineTextField(
                        labelText = core5Text,
                        inputValue = core5Input,
                        selectedConversion = core5InputUnit
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.95f)) {
                    MeasurementConverterOutLineTextField(
                        labelText = core6Text,
                        inputValue = core6Input,
                        selectedConversion = core6InputUnit
                    )
                }
            }
        }
    }

}