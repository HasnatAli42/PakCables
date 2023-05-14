package com.hsa.pakcables.components.singletons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.hsa.pakcables.R
import com.hsa.pakcables.database.StockDataBase
import com.hsa.pakcables.ui.theme.logoutText

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfilePopupMenu(avatar : MutableState<String>, logoutCalled : () -> Unit) {
    val expanded = remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded.value = true }) {
            if (avatar.value != "") {
                Image(
                    painter = rememberImagePainter(avatar.value),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_outline_24),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                )
            }
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownMenuItem(onClick = { logoutCalled() }) {
                Text(text = logoutText)
            }
        }
    }
}