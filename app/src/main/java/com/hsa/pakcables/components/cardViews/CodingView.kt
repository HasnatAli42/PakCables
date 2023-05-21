package com.hsa.pakcables.components.cardViews

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsa.pakcables.classes.DragPosition
import com.hsa.pakcables.database.tables.ItemCoding
import com.hsa.pakcables.database.tables.PartyCoding
import com.hsa.pakcables.functions.viewDateFormat
import com.hsa.pakcables.R
import com.hsa.pakcables.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun ItemCodingView(
    data : ItemCoding,
    isFirstItem : Boolean,
    isLastItem : Boolean,
    onDeletePressed : ()->Unit,
    onItemMovedUpPressed : ()->Unit,
    onItemMovedDownPressed : ()->Unit,
    onEditPressed : ()->Unit,
){
    val bgColor = lightGreen
    val tintColor = GradientBlue
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = bgColor, shape = RoundedCornerShape(15.dp))
        ,
        shape = RoundedCornerShape(15.dp)
    )
    {
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .background(bgColor)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(all = 10.dp)
            ) {
                Text(
                    data.name,
                    color = tintColor,
                    fontWeight = FontWeight.Bold,
                )
                Row() {
                    if (!isFirstItem){
                        Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                            contentDescription = moveUpRecordText,
                            tint = tintColor,
                            modifier = Modifier.clickable {
                                onItemMovedUpPressed()
                            }
                        )
                    }
                    if(!isLastItem)(
                            Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                contentDescription = movedDownRecordText,
                                tint = tintColor,
                                modifier = Modifier.clickable {
                                    onItemMovedDownPressed()
                                }
                            )
                    )
                    Icon(painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = editRecordText,
                        tint = tintColor,
                        modifier = Modifier.clickable {
                            onEditPressed()
                        }
                    )
//                    Icon(painter = painterResource(id = R.drawable.baseline_delete_outline_24),
//                        contentDescription = deleteRecordText,
//                        tint = tintColor,
//                        modifier = Modifier.clickable {
//                            onDeletePressed()
//                        }
//                    )
                }

            }
        }
    }
}

@Composable
fun PartyCodingView(
    data : PartyCoding,
    isFirstParty : Boolean,
    isLastParty : Boolean,
    onDeletePressed : ()->Unit,
    onPartyMovedUpPressed : ()->Unit,
    onPartyMovedDownPressed : ()->Unit,
    onEditPressed : ()->Unit,
){
    val bgColor = lightGreen
    val tintColor = GradientBlue
    Card(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(color = bgColor, shape = RoundedCornerShape(15.dp))
        ,
        shape = RoundedCornerShape(15.dp)
    )
    {
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .background(bgColor)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(all = 10.dp)
            ) {
                Text(
                    data.name,
                    color = tintColor,
                    fontWeight = FontWeight.Bold,
                )
                Row() {
                    if (!isFirstParty){
                        Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_up_24),
                            contentDescription = moveUpRecordText,
                            tint = tintColor,
                            modifier = Modifier.clickable {
                                onPartyMovedUpPressed()
                            }
                        )
                    }
                    if(!isLastParty)(
                            Icon(painter = painterResource(id = R.drawable.baseline_keyboard_arrow_down_24),
                                contentDescription = movedDownRecordText,
                                tint = tintColor,
                                modifier = Modifier.clickable {
                                    onPartyMovedDownPressed()
                                }
                            )
                            )
                    Icon(painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = editRecordText,
                        tint = tintColor,
                        modifier = Modifier.clickable {
                            onEditPressed()
                        }
                    )
//                    Icon(painter = painterResource(id = R.drawable.baseline_delete_outline_24),
//                        contentDescription = deleteRecordText,
//                        tint = tintColor,
//                        modifier = Modifier.clickable {
//                            onDeletePressed()
//                        }
//                    )
                }
            }
        }
    }
}