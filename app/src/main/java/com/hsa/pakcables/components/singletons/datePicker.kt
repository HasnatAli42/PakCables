package com.hsa.pakcables.components.singletons

import android.icu.util.LocaleData
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.ui.theme.cancelText
import com.hsa.pakcables.ui.theme.dateFormat
import com.hsa.pakcables.ui.theme.okText
import com.hsa.pakcables.ui.theme.pickDateText
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerField(selectedDate : MutableState<LocalDate>, otherDate : MutableState<LocalDate> ,isStartDate: Boolean , isCombined : Boolean) {
    val formattedStartDate by remember {
        derivedStateOf{
            DateTimeFormatter
                .ofPattern(dateFormat)
                .format(selectedDate.value)
        }
    }
    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        NormalPrimaryButton(event = { dateDialogState.show() }, text = formattedStartDate)
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = okText){}
            negativeButton(text = cancelText){}
        }
    ) {
        datepicker(
            initialDate = selectedDate.value,
            title = pickDateText,
            allowedDateValidator = {
                if (isStartDate && isCombined) {
                    it.dayOfMonth <= otherDate.value.dayOfMonth || it.month < otherDate.value.month
                }else if (isCombined) {
                    it.dayOfMonth >= otherDate.value.dayOfMonth || it.month > otherDate.value.month
                } else{
                    true
                }
            }
        ){
            selectedDate.value = it
        }
    }
}