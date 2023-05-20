package com.hsa.pakcables.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.R
import com.hsa.pakcables.components.cardViews.InputView
import com.hsa.pakcables.components.singletons.*
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.functions.getCurrentDate
import com.hsa.pakcables.models.InputRequestModel
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun InputContent(db: StockDataBase, currentUser: CurrentUser) {
    val selectedScreen = remember { mutableStateOf(1) }

    fun gotToMain() {
        selectedScreen.value = 1
    }

    fun gotToReport() {
        selectedScreen.value = 2
    }

    fun gotToDetail() {
        selectedScreen.value = 3
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.92f)
            .padding(all = 10.dp)
    ) {
        when (selectedScreen.value) {
            1 -> InputMainContent(
                db = db,
                currentUser = currentUser,
                gotoReport = { gotToReport() })
            2 -> InputReportContent(db)
            3 -> InputDetailContent(db)
        }
    }
}


@Composable
fun InputMainContent(db: StockDataBase, currentUser: CurrentUser, gotoReport: () -> Unit) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val remarks = remember { mutableStateOf("") }
    val lastInputID = remember { mutableStateOf(1) }
    val initial = remember { mutableStateOf(true) }
    val inputSaved = remember { mutableStateOf(false) }
    val itemCodingData: List<ItemCoding> by db.itemCodingDao.getItemCoding(currentUser.userID)
        .collectAsState(initial = emptyList())
    val inputData: List<Input> by db.inputDao.getInput(currentUser.userID)
        .collectAsState(initial = emptyList())
    if (inputData.isNotEmpty()) {
        lastInputID.value = inputData.last().inputID + 1
    }
    val input = remember { mutableListOf<InputRequestModel>() }
    if (initial.value) {
        itemCodingData.forEach {
            initial.value = false
            inputSaved.value = false
            input.add(InputRequestModel(itemName = it.name))
        }
    }
    fun saveInput() {
        Log.d("userTest", input.toString())
        input.forEach { data ->
            val insertInput: Input = Input(
                red = data.red,
                black = data.black,
                yellow = data.yellow,
                blue = data.blue,
                green = data.green,
                white = data.white,
                other = data.other,
                total = data.total,
                core2 = data.core2,
                core3 = data.core3,
                core4 = data.core4,
                core5 = data.core5,
                core6 = data.core6,
                createdDate = getCurrentDate(),
                lastUpdated = getCurrentDate(),
                itemName = data.itemName,
                itemCodingID = data.itemID,
                inputID = lastInputID.value,
                userID = currentUser.userID,
                remarks = remarks.value,
            )
            coroutineScope.launch {
                db.inputDao.upsertInput(input = insertInput)
            }
        }
        textToast(text = inputInsertSuccessText, context = context)
        input.clear()
        remarks.value = ""
        inputSaved.value = true
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.91f)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        input.forEach {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            InputView(input = it)
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            OutLinedInputWithTrailingIcon(
                stringMutableState = remarks,
                labelText = remarksText,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                painter = painterResource(id = R.drawable.baseline_comment_24)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(0.4f)) {
            NormalPrimaryButton(event = { if(!inputSaved.value) {saveInput()}else{initial.value = true} }, text = if(!inputSaved.value) {saveText}else{saveAgainText})
        }
        SymbolGradientCircleButton(
            event = { gotoReport() },
            icon = painterResource(id = R.drawable.baseline_assignment_24)
        )
    }
}

@Composable
fun InputReportContent(db: StockDataBase) {

}

@Composable
fun InputDetailContent(db: StockDataBase) {

}