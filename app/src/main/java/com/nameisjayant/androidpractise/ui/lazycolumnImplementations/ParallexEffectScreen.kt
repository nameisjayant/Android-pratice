package com.nameisjayant.androidpractise.ui.lazycolumnImplementations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nameisjayant.androidpractise.R

@Composable
fun ParallaxEffectScreen(
    modifier: Modifier = Modifier
) {
    val headerHeight = 250.dp
    val lazyListState = rememberLazyListState()
    val headerHeightToPx = with(LocalDensity.current) {
        headerHeight.toPx()
    }
    val scrollPx by remember {
        derivedStateOf {
            if (lazyListState.firstVisibleItemIndex == 0)
                lazyListState.firstVisibleItemScrollOffset.toFloat()
            else
                headerHeightToPx
        }
    }
    val translationY by remember {
        derivedStateOf {
            -(scrollPx * 0.4f)
        }
    }

    Box {
        ParallaxImage(translation = translationY, height = headerHeight)
        ListItems(
            lazyListState = lazyListState,
            headerHeight = headerHeight
        )
    }
}

@Composable
fun ListItems(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    headerHeight: Dp
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(headerHeight))
        }
        items(20) {
            Text(
                text = "This is item #$it",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun ParallaxImage(translation: Float, height: Dp) {
    Image(
        painter = painterResource(id = R.drawable.download),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .graphicsLayer {
                translationY = translation
            },
        contentScale = ContentScale.Crop
    )
}