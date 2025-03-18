package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


@Composable
fun PointerInputScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(Color.Red, RoundedCornerShape(8.dp))
                .size(100.dp)
                .pointerInput(
                    key1 = null
                ) {
                    awaitPointerEventScope {
                        val event = awaitPointerEvent()
                    }
                    detectTapGestures {

                    }
                }
        )
    }
}

enum class DragValue {
    START,
    END
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomSwipeable(
    modifier: Modifier = Modifier
) {
    val swipeableState = rememberDraggableState {

    }
    val anchors = DraggableAnchors {
        DragValue.START at -70f
        DragValue.END at 70f
    }
//    val state = remember {
//        AnchoredDraggableState(anchors = anchors, positionalThreshold = {
//
//        }, velocityThreshold = {}, snapAnimationSpec = {}, decayAnimationSpec = {})
//    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .background(
                    Color(0XFFFF597B),
                    CircleShape
                )
                .padding(start = 5.dp)
                .width(70.dp)
                .height(40.dp)
//                .anchoredDraggable(
//                    state = swipeableState,
//                    orientation = Orientation.Horizontal,
//                    reverseDirection = false
//                )
            ,
            contentAlignment = Alignment.CenterStart
        ) {
            Spacer(
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White, CircleShape)
            )
        }
    }
}