package com.nameisjayant.androidpractise.ui.custom_layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout


@Composable
fun CustomColumn(
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

        var yPosition = 0

        val width = placeable.maxOfOrNull { it.width } ?: 0
        val height = placeable.sumOf { it.height }

        layout(width, height) {
            placeable.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}