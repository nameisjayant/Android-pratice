package com.nameisjayant.androidpractise.ui.custom_layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun CustomRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurable, constraints ->

        val placeable = measurable.map { measurable ->
            measurable.measure(constraints)
        }

        var xPosition = 0

        val width = placeable.sumOf { it.width }
        val height = placeable.maxOfOrNull { it.height } ?: 0

        layout(width, height) {
            placeable.forEach { placeable ->
                placeable.placeRelative(x = xPosition, y = 0)
                xPosition += placeable.width
            }
        }
    }
}