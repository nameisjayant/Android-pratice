package com.nameisjayant.androidpractise.ui.canvas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.androidpractise.R


@Composable
fun CutCornerScreen(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.hello_world_s), style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Red
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
            .shadow(20.dp, shape = CutCornerShape(32.dp))
            .border(width = 2.dp, color = Color.Red, shape = CutCornerShape(32.dp))
            .graphicsLayer {
                //   shadowElevation = 8.dp.toPx()
                shape = CutCornerShape(32.dp)
                clip = true
            }
            .background(
                Color.Yellow,
                //  shape = CutCornerShape(32.dp)
            )
            .padding(32.dp)
    )
}