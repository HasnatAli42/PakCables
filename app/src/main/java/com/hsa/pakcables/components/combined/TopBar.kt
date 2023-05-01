package com.hsa.pakcables.components.combined

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsa.pakcables.ui.theme.RobotoCondensed
import com.hsa.pakcables.ui.theme.gradientBlackGray
import com.hsa.pakcables.ui.theme.gradientGrayBlack
import com.hsa.pakcables.R
import com.hsa.pakcables.components.singletons.MainHeadingTextCenter
import com.hsa.pakcables.components.singletons.MainHeadingTextStart
import com.hsa.pakcables.ui.theme.pakCables

@Composable
fun MainTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(gradientBlackGray)
                .padding(top = 5.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_electrical_services_24),
                contentDescription = "",
                Modifier.size(40.dp, 40.dp)
            )
            MainHeadingTextStart(text = pakCables)
        }
    }
}

@Composable
fun HomeTopBar(
    profileName : MutableState<String>,
    profileImage : MutableState<String>,
    profilePicClicked : ()->Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
            .fillMaxWidth(1f)
            .background(gradientBlackGray)
            .padding(all = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(id = R.drawable.baseline_electrical_services_24),
                    contentDescription = "",
                    Modifier.size(30.dp, 30.dp)
                )
                Spacer(Modifier.padding(start = 5.dp))
                MainHeadingTextCenter(text = pakCables)
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { profilePicClicked() }) {
                Text(text = profileName.value, fontFamily = RobotoCondensed, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(Modifier.padding(start = 5.dp))
                ProfilePicture(avatar = profileImage)
            }
        }

    }

}