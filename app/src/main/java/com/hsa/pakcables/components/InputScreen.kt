package com.hsa.pakcables.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.R
import com.hsa.pakcables.components.singletons.*
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.ui.theme.saveText
import com.hsa.pakcables.ui.theme.searchText

@Composable
fun InputContent(db : StockDataBase, currentUser: CurrentUser) {
    val selectedScreen = remember {mutableStateOf(1)}

    fun gotToMain(){
        selectedScreen.value = 1
    }
    fun gotToReport(){
        selectedScreen.value = 2
    }
    fun gotToDetail(){
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
        when(selectedScreen.value){
            1 -> InputMainContent(db = db, currentUser = currentUser, gotoReport = {gotToReport()})
            2 -> InputReportContent(db)
            3 -> InputDetailContent(db)
        }
    }
}


@Composable
fun InputMainContent(db : StockDataBase,currentUser: CurrentUser, gotoReport : ()->Unit){
    val scroll = rememberScrollState()
    val search = remember { mutableStateOf("") }
    val itemCodingData: List<ItemCoding> by db.itemCodingDao.getItemCoding(currentUser.userID)
        .collectAsState(initial = emptyList())
    OutLinedInputWithSearch(stringMutableState = search, labelText = searchText, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.91f)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemCodingData.forEach {
            MeasurementConverterOutLineTextField(labelText = it.name)
        }
    }
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)){
        Row(modifier = Modifier.fillMaxWidth(0.33f)){
            NormalPrimaryButton(event = {  }, text = saveText)
        }
        SymbolGradientCircleButton(event = {gotoReport()}, icon = painterResource(id = R.drawable.baseline_assignment_24))
    }
}

@Composable
fun InputReportContent(db : StockDataBase){

}

@Composable
fun InputDetailContent(db : StockDataBase){

}