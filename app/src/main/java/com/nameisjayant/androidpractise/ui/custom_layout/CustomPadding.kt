package com.nameisjayant.androidpractise.ui.custom_layout

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

@Composable
fun CustomPadding(modifier: Modifier = Modifier) {
    Text("Hello World", modifier = modifier.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(20, 200)
        }
    })
}