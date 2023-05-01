package com.hsa.pakcables.components.singletons

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.hsa.pakcables.ui.theme.RobotoCondensed
import com.hsa.pakcables.ui.theme.colorOff
import com.hsa.pakcables.ui.theme.colorOn
import com.hsa.pakcables.ui.theme.lightGreen

@Composable
fun MainHeadingTextCenter(text : String){
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = lightGreen,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun MainHeadingTextStart(text : String){
    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = lightGreen,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun HeadingTextCenterWhite(text : String){
    Text(
        text = text, fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun HeadingTextCenterBlack(text : String){
    Text(
        text = text, fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun DescriptionTextStart(text : String){
    Text(
        text = text, fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        textAlign = TextAlign.Start,
    )
}

@Composable
fun ClickableDescriptionTextStart(text : String, onClick: ()-> Unit){
    Text(
        text = text, fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        textAlign = TextAlign.Center,
        modifier = Modifier.clickable(onClick = { onClick() })
    )
}

@Composable
fun BottomBarTabsText(text : String, selectedTab: MutableState<Int>, tabValue: Int){
    Text(
        text,
        fontFamily = RobotoCondensed,
        fontSize = 10.sp,
        color = if (selectedTab.value == tabValue) {
            colorOn
        } else {
            colorOff
        }
    )
}