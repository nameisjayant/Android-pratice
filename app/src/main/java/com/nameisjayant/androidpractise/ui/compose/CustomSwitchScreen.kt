package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

inline val String.stringState
    @Composable get() = remember { mutableStateOf(this) }


@Composable
fun CustomSwitchScreen(
    modifier: Modifier = Modifier
) {
    var offsetX by remember {
        mutableFloatStateOf(0.0f)
    }
    val parentWidth = 70.dp
    val indicatorSize = 30.dp
    val parentWidthPx = with(LocalDensity.current) {
        parentWidth.toPx()
    }
    val indicatorSizePx = with(LocalDensity.current) {
        indicatorSize.toPx()
    }
    var isOn by remember { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(
                    if (isOn) Color(0XFFFF597B) else Color(0XFFF9B5D0),
                    CircleShape
                )
                .padding(horizontal = 5.dp)
                .width(parentWidth)
                .height(40.dp)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            isOn = if (offsetX > (parentWidthPx - indicatorSizePx) / 2) {
                                offsetX = parentWidthPx - indicatorSizePx
                                true
                            } else {
                                offsetX = 0f
                                false
                            }
                        }
                    ) { change, dragAmount ->
                        change.consume()
                        offsetX = (offsetX + dragAmount.x).coerceIn(
                            0f, parentWidthPx - indicatorSizePx
                        )
                    }
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Spacer(
                modifier = Modifier
                    .size(indicatorSize)
                    .offset {
                        IntOffset(offsetX.toInt(), 0)
                    }
                    .background(Color.White, CircleShape)

            )
        }
    }
}

fun Modifier.swipeable(
    onChange: (Boolean) -> Unit,
    offsetX: Float,
    parentWidthPx: Float,
    indicatorSizePx: Float,
    onOffsetChange: (Float) -> Unit
): Modifier =
    this.pointerInput(Unit) {
        detectDragGestures(
            onDragEnd = {
                if (offsetX > (parentWidthPx - indicatorSizePx) / 2) {
                    onOffsetChange(parentWidthPx - indicatorSizePx)
                    onChange(true)
                } else {
                    onOffsetChange(0f)
                    onChange(false)
                }
            }
        ) { change, dragAmount ->
            change.consume()
            onOffsetChange(
                (offsetX + dragAmount.x).coerceIn(
                    0f, parentWidthPx - indicatorSizePx
                )
            )
        }
    }


