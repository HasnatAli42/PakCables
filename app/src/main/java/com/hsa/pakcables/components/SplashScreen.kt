package com.hsa.pakcables.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hsa.pakcables.R
import com.hsa.pakcables.components.combined.MainTopBar
import com.hsa.pakcables.components.singletons.HeadingTextCenter
import com.hsa.pakcables.components.singletons.MainHeadingTextCenter
import com.hsa.pakcables.ui.theme.getStarted
import com.hsa.pakcables.ui.theme.gradientGrayBlack
import com.hsa.pakcables.ui.theme.pakCables
import com.hsa.pakcables.ui.theme.welcomeMsg

@Composable
fun SplashScreen(onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(gradientGrayBlack)
    ){
    MainTopBar ()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(gradientGrayBlack)
                .clickable(onClick = onClick),
        ) {
            MainHeadingTextCenter(text = pakCables)
            HeadingTextCenter(text = welcomeMsg)
            HeadingTextCenter(text = getStarted)
            Image(
                painter = painterResource(R.drawable.pakcables2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.5f)
            )
        }
    }
}