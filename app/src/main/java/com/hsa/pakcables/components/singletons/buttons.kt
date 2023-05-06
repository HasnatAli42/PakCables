package com.hsa.pakcables.components.singletons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.hsa.pakcables.ui.theme.Blue
import com.hsa.pakcables.ui.theme.GradientBlue
import com.hsa.pakcables.ui.theme.GradientPurple
import com.hsa.pakcables.ui.theme.gradientBlackGray

@Composable
fun NormalPrimaryButton (event : ()-> Unit, text: String){
    Button(
        onClick = { event() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Blue,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}

@Composable
fun SymbolGradientCircleButton (event : ()-> Unit, icon : Painter){
    Button(
        onClick = { event() },
        modifier = Modifier
            .size(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = GradientBlue,
            contentColor = MaterialTheme.colors.onPrimary
        ),
        shape = CircleShape,
    ) {
        Icon(
            painter = icon,
            contentDescription = "View Reports",
            modifier = Modifier.size(width = 30.dp, height = 30.dp)
        )
    }
}