package com.nameisjayant.androidpractise.ui.custom_layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun CustomScrollIndicatorLazyColumn(
    modifier: Modifier = Modifier
) {

    val listState = rememberLazyListState()
    var trackHeightPx by remember { mutableIntStateOf(0) }

    val scrollProgress by remember {
        derivedStateOf {
            val firstVisibleItem = listState.firstVisibleItemIndex
            val totalItems = listState.layoutInfo.totalItemsCount
            if (totalItems > 1)
                (firstVisibleItem.toFloat() / (totalItems - 1)).coerceIn(0f, 1f)
            else 0f
        }
    }

    Box(modifier = modifier.fillMaxSize()) {

        // Main scrollable list
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(100) {
                Text("Item #$it", modifier = Modifier.padding(16.dp))
            }
        }

        // Custom scrollbar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
                .align(Alignment.CenterEnd)
                .padding(vertical = 12.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    trackHeightPx = layoutCoordinates.size.height
                }
        ) {
            // Scroll thumb
            val thumbHeightPx = with(LocalDensity.current) { 40.dp.toPx() }
            val offsetY = ((trackHeightPx - thumbHeightPx) * scrollProgress).toInt()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .offset { IntOffset(x = 0, y = offsetY) }
                    .background(Color.Gray, RoundedCornerShape(3.dp))
            )
        }
    }
}