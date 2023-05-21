package com.hsa.pakcables.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.hsa.pakcables.components.cardViews.AddInputView
import com.hsa.pakcables.components.cardViews.InputDetailView
import com.hsa.pakcables.components.cardViews.InputListView
import com.hsa.pakcables.components.singletons.*
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.Input
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.functions.nullIntegerHandler
import com.hsa.pakcables.functions.getCurrentDate
import com.hsa.pakcables.functions.updateBasedOnUnits
import com.hsa.pakcables.models.InputRequestModel
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputContent(db: StockDataBase, currentUser: CurrentUser) {
    val selectedScreen = remember { mutableStateOf(1) }
    val selectedRecordID = remember { mutableStateOf(1) }
    val startDate = remember{ mutableStateOf(LocalDate.now())}
    val endDate = remember{ mutableStateOf(LocalDate.now().plusDays(1))}
    fun goToMain() {
        selectedScreen.value = 1
    }

    fun goToReport() {
        selectedScreen.value = 2
    }

    fun goToDetail() {
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
                gotoReport = { goToReport() })
            2 -> InputReportContent(
                db = db,
                currentUser = currentUser,
                goToMain = { goToMain() },
                selectedRecord = {
                    id ->  selectedRecordID.value = id
                    goToDetail()
                },
                startDate = startDate,
                endDate = endDate
            )
            3 -> InputDetailContent(
                db = db,
                currentUser = currentUser,
                recordID = selectedRecordID,
                gotoReport = { goToReport() }
            )
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
    val initialIndex = remember { mutableStateOf(true) }
    val inputSaved = remember { mutableStateOf(false) }
    val itemCodingData: List<ItemCoding> by db.itemCodingDao.getItemCoding(currentUser.userID)
        .collectAsState(initial = emptyList())
    val inputData: List<Input> by db.inputDao.getInput(currentUser.userID)
        .collectAsState(initial = emptyList())
    if (inputData.isNotEmpty() && initialIndex.value) {
        initialIndex.value = false
        lastInputID.value = inputData.last().inputID + 1
    }
    val input = remember { mutableListOf<InputRequestModel>() }
    if (initial.value) {
        itemCodingData.forEach {
            initial.value = false
            inputSaved.value = false
            input.add(InputRequestModel(itemName = it.name, itemID = nullIntegerHandler(integer = it.id)))
        }
    }
    fun saveInput() {
        input.forEach { data ->
            data.core2 = updateBasedOnUnits(amount = data.core2, unit = data.core2Unit)
            data.core3 = updateBasedOnUnits(amount = data.core3, unit = data.core3Unit)
            data.core4 = updateBasedOnUnits(amount = data.core4, unit = data.core4Unit)
            data.core5 = updateBasedOnUnits(amount = data.core5, unit = data.core5Unit)
            data.core6 = updateBasedOnUnits(amount = data.core6, unit = data.core6Unit)
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
            .fillMaxHeight(0.9f)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        input.forEach {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            AddInputView(input = it)
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            OutLinedInputWithTrailingIcon(
                stringMutableState = remarks,
                labelText = remarksText,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                painter = painterResource(id = R.drawable.baseline_comment_24)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(0.4f)) {
            NormalPrimaryButton(event = { if(!inputSaved.value) { if(input.isNotEmpty() && remarks.value.isNotEmpty()){saveInput()}}else{initial.value = true} }, text = if(!inputSaved.value) {saveText}else{saveAgainText})
        }
        SymbolGradientCircleButton(
            event = { gotoReport() },
            icon = painterResource(id = R.drawable.baseline_assignment_24)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputReportContent(
    db: StockDataBase,
    goToMain: ()->Unit,
    currentUser: CurrentUser,
    selectedRecord : (id :Int)-> Unit,
    startDate : MutableState<LocalDate>,
    endDate : MutableState<LocalDate>
) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val initial = remember{ mutableStateOf(true)}
    val inputData: List<Input> by db.inputDao.getInput(currentUser.userID)
        .collectAsState(initial = emptyList())
    val inputToView = remember { mutableListOf<Input>()}
   val filteredList = remember { mutableListOf<Input>()}
    if (initial.value){
            inputData.forEach{
                initial.value = false
                if(inputToView.none { data -> data.inputID == it.inputID }){
                    inputToView.add(it)
                }
            }
    }
    if (startDate.value.isBefore(endDate.value) || startDate.value.isAfter(endDate.value) || startDate.value.isEqual(endDate.value)){
        inputToView.clear()
        filteredList.clear()
        inputData.forEach{
            if(inputToView.none { data -> data.inputID == it.inputID }){
                inputToView.add(it)
            }
        }
        inputToView.filter {
            it.lastUpdated.time >= startDate.value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() && it.lastUpdated.time <= endDate.value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        }.forEach {
            filteredList.add(it)
        }
    }
    HeadingTextCenterBlack(text = inputRecordsText)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.91f)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (filteredList.isNotEmpty()){
            filteredList.forEach{
                Spacer(modifier = Modifier.padding(top = 10.dp))
                InputListView(input = it, recordClicked = {id -> selectedRecord(id)})
            }
        }else{
            HeadingTextCenterBlack(text = noRecordFoundText)
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.35f)) {
            DatePickerField(
                selectedDate = startDate,
                otherDate = endDate,
                isStartDate = true,
                isCombined = true
            )
        }
        Row(modifier = Modifier.fillMaxWidth(0.5f)) {
            DatePickerField(
                selectedDate = endDate,
                otherDate = startDate,
                isStartDate = false,
                isCombined = true
            )
        }
        SymbolGradientCircleButton(
            event = { goToMain() },
            icon = painterResource(id = R.drawable.baseline_arrow_back_24)
        )
    }

}

@Composable
fun InputDetailContent(
    db: StockDataBase,
    recordID : MutableState<Int>,
    currentUser: CurrentUser,
    gotoReport : ()-> Unit
) {
    val scroll = rememberScrollState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val inputDetailData: List<Input> by db.inputDao.getInputByID(currentUser.userID, recordID.value )
        .collectAsState(initial = emptyList())

    HeadingTextCenterBlack(text = inputRecordsDetailsText)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.91f)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        inputDetailData.forEach{
            Spacer(modifier = Modifier.padding(top = 10.dp))
            InputDetailView(it)
        }
    }

    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        SymbolGradientCircleButton(
            event = { gotoReport() },
            icon = painterResource(id = R.drawable.baseline_arrow_back_24)
        )
    }
}