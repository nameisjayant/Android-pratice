package com.nameisjayant.androidpractise.ui.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp


@Composable
fun PointerInputTapGesture(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red)
                .pointerInput(key1 = Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            // double tap
                        },
                        onLongPress = {
                            // long press
                        },
                        onPress = { offset ->
                            Log.d("Gesture", "Finger touched at $offset")
                            tryAwaitRelease()
                            Log.d("Gesture", "Finger released at $offset")
                        },
                        onTap = {
                            // on tap
                        }
                    )
                }
        )
    }
}

@Composable
fun PointerInputDragGesture(
    modifier: Modifier = Modifier
) {
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var color by remember {
        mutableStateOf(Color.Red)
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offset.x.toInt(), offset.y.toInt())
                }
                .size(200.dp)
                .background(color)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            color = Color.Black
                        },
                        onDragEnd = {
                            color = Color.Red
                        },
                        onDragCancel = {
                            color = Color.Yellow
                        },
                        onDrag = { change, dragAmount ->
                            offset += dragAmount
                        }
                    )
                }
        )
    }
}

@Composable
fun PointerInputDragHorizontalGesture(
    modifier: Modifier = Modifier
) {
    var offset by remember {
        mutableFloatStateOf(0.0f)
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offset.toInt(), 0)
                }
                .size(200.dp)
                .background(Color.Red)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->

                        },
                        onDragEnd = {

                        },
                        onDragCancel = {

                        },
                        onHorizontalDrag = { change, dragAmount ->
                            offset += dragAmount
                        }
                    )
                }
        )
    }
}

@Composable
fun PointerInputDragVerticalGesture(
    modifier: Modifier = Modifier
) {
    var offset by remember {
        mutableFloatStateOf(0.0f)
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(0, offset.toInt())
                }
                .size(200.dp)
                .background(Color.Red)
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragStart = { offset ->

                        },
                        onDragEnd = {

                        },
                        onDragCancel = {

                        },
                        onVerticalDrag = { change, dragAmount ->
                            offset += dragAmount
                        }
                    )

                }
        )
    }
}

@Composable
fun PointerInputDragGestureAfterLongPress(
    modifier: Modifier = Modifier
) {
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offset.x.toInt(), offset.y.toInt())
                }
                .size(200.dp)
                .background(Color.Red)
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {

                        },
                        onDragEnd = {

                        },
                        onDragCancel = {

                        },
                        onDrag = { change, dragAmount ->
                            offset += dragAmount
                        }
                    )
                }
        )
    }
}

@Composable
fun PointerInputTransformGesture(
    modifier: Modifier = Modifier
) {
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var color by remember {
        mutableStateOf(Color.Red)
    }
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offset.x.toInt(), offset.y.toInt())
                }
                .size(200.dp)
                .background(color)
                .graphicsLayer(scaleX = scale, scaleY = scale, rotationZ = rotation)
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true,
                        onGesture = { centroid, pan, zoom, rotationChange ->
                            offset += pan
                            scale *= zoom
                            rotation += rotationChange
                        }
                    )
                }
        )
    }
}

@Composable
fun PointerInputCustomGesture(
    modifier: Modifier = Modifier
) {
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }
    var color by remember {
        mutableStateOf(Color.Red)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(offset.x.toInt(), offset.y.toInt())
                }
                .size(200.dp)
                .background(color)
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true){
                            val event = awaitPointerEvent()
                            if(event.changes.any { it.changedToUp() }){

                            }
                        }
                    }
                }
        )
    }
}
