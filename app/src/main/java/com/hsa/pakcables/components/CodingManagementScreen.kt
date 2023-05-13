package com.hsa.pakcables.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.components.cardViews.ItemCodingView
import com.hsa.pakcables.components.cardViews.PartyCodingView
import com.hsa.pakcables.components.combined.InputRowForItemCoding
import com.hsa.pakcables.components.combined.InputRowForPartyCoding
import com.hsa.pakcables.components.combined.ViewSwitches_2
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.database.tables.CurrentUser
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.functions.getCurrentDate
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun CodingContent(db: StockDataBase) {
    val selectedSwitch = remember { mutableStateOf(1) }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.92f)
    ) {
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ViewSwitches_2(
            selectedSwitch = selectedSwitch,
            labels = arrayOf(itemCodingText, partyCodingText)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        when (selectedSwitch.value) {
            1 -> ItemCodingContent(db = db)
            2 -> PartyCodingContent(db = db)
        }
    }
}

@Composable
fun ItemCodingContent(db: StockDataBase) {
    val inputItemName = remember {
        mutableStateOf("")
    }
    val inputItemNameError = remember {
        mutableStateOf("")
    }
    val buttonText = remember {
        mutableStateOf(addText)
    }
    val editedItem = remember { mutableListOf<ItemCoding>() }
    val coroutineScope = rememberCoroutineScope()
    val currentUserCheck : List<CurrentUser> by db.currentUserDao.getUserById().collectAsState(
        initial = emptyList()
    )
    if(currentUserCheck.isNotEmpty()){
        val itemCodingData: List<ItemCoding> by db.itemCodingDao.getItemCoding(currentUserCheck[0].userID)
            .collectAsState(initial = emptyList())
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            InputRowForItemCoding(
                stringMutableState = inputItemName,
                errorMutableState = inputItemNameError,
                buttonText = buttonText.value
            ) {
                if (inputItemName.value.isNotEmpty()) {
                    inputItemNameError.value = ""
                    coroutineScope.launch {
                        if (buttonText.value == updateText){
                            buttonText.value = addText
                            db.itemCodingDao.upsertItemCoding(
                                ItemCoding(
                                    name = inputItemName.value,
                                    sortOrder = editedItem.last().sortOrder,
                                    createdDate = editedItem.last().createdDate,
                                    lastUpdated = getCurrentDate(),
                                    id = editedItem.last().id,
                                    userId = currentUserCheck[0].userID
                                )
                            )
                        }else{
                            db.itemCodingDao.upsertItemCoding(
                                ItemCoding(
                                    name = inputItemName.value,
                                    sortOrder = if (itemCodingData.isNotEmpty()) {
                                        itemCodingData.last().sortOrder + 1
                                    } else {
                                        1.0
                                    },
                                    createdDate = getCurrentDate(),
                                    lastUpdated = getCurrentDate(),
                                    userId = currentUserCheck[0].userID
                                ))
                        }
                        inputItemName.value = ""
                    }
                } else {
                    inputItemNameError.value = fieldRequiredText
                }
            }
            ItemCodingListView(
                itemCodingData = itemCodingData,
                onDeletePressed = { itemToDelete ->
                    coroutineScope.launch {
                        db.itemCodingDao.deleteItemCoding(itemToDelete)
                    }
                },
                onItemMovedUpPressed = { itemToMoveUp ->
                    val itemIndex = itemCodingData.indexOf(itemToMoveUp)
                    val itemToShiftUp = itemCodingData[itemIndex]
                    val itemToShiftDown = itemCodingData[itemIndex - 1]
                    itemToShiftUp.sortOrder = itemToShiftUp.sortOrder - 1
                    itemToShiftDown.sortOrder = itemToShiftDown.sortOrder + 1
                    itemToShiftUp.lastUpdated = getCurrentDate()
                    itemToShiftDown.lastUpdated = getCurrentDate()
                    coroutineScope.launch {
                        db.itemCodingDao.upsertItemCoding(itemToShiftUp)
                        db.itemCodingDao.upsertItemCoding(itemToShiftDown)
                    }
                },
                onItemMovedDownPressed = { itemToMoveDown ->
                    val itemIndex = itemCodingData.indexOf(itemToMoveDown)
                    val itemToShiftUp = itemCodingData[itemIndex + 1]
                    val itemToShiftDown = itemCodingData[itemIndex]
                    itemToShiftUp.sortOrder = itemToShiftUp.sortOrder - 1
                    itemToShiftDown.sortOrder = itemToShiftDown.sortOrder + 1
                    itemToShiftUp.lastUpdated = getCurrentDate()
                    itemToShiftDown.lastUpdated = getCurrentDate()
                    coroutineScope.launch {
                        db.itemCodingDao.upsertItemCoding(itemToShiftUp)
                        db.itemCodingDao.upsertItemCoding(itemToShiftDown)
                    }
                },
                onEditPressed = { itemToEdit ->
                    editedItem.add(itemToEdit)
                    buttonText.value = updateText
                    inputItemName.value = itemToEdit.name
                }
            )
        }
    }

}

@Composable
fun ItemCodingListView(
    itemCodingData: List<ItemCoding>,
    onDeletePressed: (item: ItemCoding) -> Unit,
    onItemMovedDownPressed: (item: ItemCoding) -> Unit,
    onItemMovedUpPressed: (item: ItemCoding) -> Unit,
    onEditPressed: (item: ItemCoding) -> Unit
) {
    val scroll = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .verticalScroll(scroll)
    ) {

        itemCodingData.forEach { item ->
            Spacer(modifier = Modifier.padding(top = 10.dp))
            ItemCodingView(
                data = item,
                isFirstItem = itemCodingData.indexOf(item) == 0,
                isLastItem = itemCodingData.indexOf(item) == itemCodingData.size - 1,
                onDeletePressed = { onDeletePressed(item) },
                onItemMovedDownPressed = { onItemMovedDownPressed(item) },
                onItemMovedUpPressed = { onItemMovedUpPressed(item) },
                onEditPressed = { onEditPressed(item) },
            )
        }
    }
}

@Composable
fun PartyCodingContent(db: StockDataBase) {
    val inputPartyName = remember {
        mutableStateOf("")
    }
    val inputPartyNameError = remember {
        mutableStateOf("")
    }
    val buttonText = remember {
        mutableStateOf(addText)
    }
    val coroutineScope = rememberCoroutineScope()
    val currentUserCheck : List<CurrentUser> by db.currentUserDao.getUserById().collectAsState(
        initial = emptyList()
    )
    if (currentUserCheck.isNotEmpty()){
        val partyCodingData: List<PartyCoding> by db.partyCodingDao.getPartyCoding(currentUserCheck[0].userID)
            .collectAsState(initial = emptyList())
        val editedItem = remember { mutableListOf<PartyCoding>() }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            InputRowForPartyCoding(
                stringMutableState = inputPartyName,
                errorMutableState = inputPartyNameError,
                buttonText = buttonText.value
            ) {
                if (inputPartyName.value.isNotEmpty()) {
                    inputPartyNameError.value = ""
                    coroutineScope.launch {
                        if (buttonText.value == updateText){
                            buttonText.value = addText
                            db.partyCodingDao.upsertPartyCoding(
                                PartyCoding(
                                    name = inputPartyName.value,
                                    sortOrder = editedItem.last().sortOrder,
                                    createdDate = editedItem.last().createdDate,
                                    lastUpdated = getCurrentDate(),
                                    id = editedItem.last().id,
                                    userId = currentUserCheck[0].userID
                                )
                            )
                        }else{
                            db.partyCodingDao.upsertPartyCoding(
                                PartyCoding(
                                    name = inputPartyName.value, sortOrder = if (partyCodingData.isNotEmpty()) {
                                        partyCodingData.last().sortOrder + 1
                                    } else {
                                        1.0
                                    }, createdDate = getCurrentDate(), lastUpdated = getCurrentDate(),
                                    userId = currentUserCheck[0].userID
                                ))
                        }
                        inputPartyName.value = ""
                    }
                } else {
                    inputPartyNameError.value = fieldRequiredText
                }
            }
            PartyCodingListView(
                partyCodingData = partyCodingData,
                onDeletePressed = { partyToDelete ->
                    coroutineScope.launch {
                        db.partyCodingDao.deletePartyCoding(partyToDelete)
                    }
                },
                onPartyMovedUpPressed = { partyToMoveUp ->
                    val itemIndex = partyCodingData.indexOf(partyToMoveUp)
                    val partyToShiftUp = partyCodingData[itemIndex]
                    val partyToShiftDown = partyCodingData[itemIndex - 1]
                    partyToShiftUp.sortOrder = partyToShiftUp.sortOrder - 1
                    partyToShiftDown.sortOrder = partyToShiftDown.sortOrder + 1
                    partyToShiftUp.lastUpdated = getCurrentDate()
                    partyToShiftDown.lastUpdated = getCurrentDate()
                    coroutineScope.launch {
                        db.partyCodingDao.upsertPartyCoding(partyToShiftUp)
                        db.partyCodingDao.upsertPartyCoding(partyToShiftDown)
                    }
                },
                onPartyMovedDownPressed = { partyToMoveDown ->
                    val itemIndex = partyCodingData.indexOf(partyToMoveDown)
                    val partyToShiftUp = partyCodingData[itemIndex + 1]
                    val partyToShiftDown = partyCodingData[itemIndex]
                    partyToShiftUp.sortOrder = partyToShiftUp.sortOrder - 1
                    partyToShiftDown.sortOrder = partyToShiftDown.sortOrder + 1
                    partyToShiftUp.lastUpdated = getCurrentDate()
                    partyToShiftDown.lastUpdated = getCurrentDate()
                    coroutineScope.launch {
                        db.partyCodingDao.upsertPartyCoding(partyToShiftUp)
                        db.partyCodingDao.upsertPartyCoding(partyToShiftDown)
                    }
                },
                onEditPressed = { partyToEdit ->
                    editedItem.add(partyToEdit)
                    buttonText.value = updateText
                    inputPartyName.value = partyToEdit.name
                }

            )
        }
    }
}

@Composable
fun PartyCodingListView(
    partyCodingData: List<PartyCoding>,
    onDeletePressed: (party: PartyCoding) -> Unit,
    onPartyMovedDownPressed: (party: PartyCoding) -> Unit,
    onPartyMovedUpPressed: (party: PartyCoding) -> Unit,
    onEditPressed: (party: PartyCoding) -> Unit
) {
    val scroll = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .verticalScroll(scroll)
    ) {

        partyCodingData.forEach {   party ->
            Spacer(modifier = Modifier.padding(top = 10.dp))
            PartyCodingView(
                data = party,
                isFirstParty = partyCodingData.indexOf(party) == 0,
                isLastParty = partyCodingData.indexOf(party) == partyCodingData.size - 1,
                onDeletePressed = { onDeletePressed(party) },
                onPartyMovedDownPressed = { onPartyMovedDownPressed(party) },
                onPartyMovedUpPressed = { onPartyMovedUpPressed(party) },
                onEditPressed = { onEditPressed(party) },)
        }
    }
}
