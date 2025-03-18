package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ContentPadding(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Hello World", modifier = Modifier
            .drawBehind {
                drawRect(
                    color = Color.Black
                )
            }
            .padding(20.dp), color = Color.White)
    }
}


@Composable
fun OffsetScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth(0.7f)
                .height(150.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Spacer(
                modifier = Modifier
                    .offset(x = 0.dp, y = -(50.dp)) // move the content to the top
                    .background(Color.Red, CircleShape)
                    .size(100.dp)
            )
        }
    }
}
