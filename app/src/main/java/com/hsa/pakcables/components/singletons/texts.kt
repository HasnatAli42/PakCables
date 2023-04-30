package com.hsa.pakcables.components.singletons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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
fun HeadingTextCenter(text : String){
    Text(
        text = text, fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun DescriptionTextStart(text : String){
    Text(
        text = text, fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        textAlign = TextAlign.Start,
    )
}