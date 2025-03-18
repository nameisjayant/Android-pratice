package com.nameisjayant.androidpractise.ui.compose

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp


@Composable
fun GraphicLayerScaling(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .graphicsLayer(
                    scaleX = 2.5f,
                    scaleY = 2.5f
                )
                .background(Color.Blue)
        )
    }

}


@Composable
fun GraphicLayerRotation(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    rotationZ = 45f,
                    rotationX = 45f,
                    rotationY = 45f
                )
                .background(Color.Blue)
        )
    }
}

@Composable
fun GraphicLayerTranslation(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    translationX = 100f,
                    translationY = 150f
                )
                .background(Color.Blue)
        )
    }
}

@Composable
fun GraphicLayerAlpha(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    alpha = 0.5f
                )
                .background(Color.Blue)
        )
    }
}

@Composable
fun GraphicLayerShadow(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    shadowElevation = 100f,
                    spotShadowColor = Color.Red
                )
                .background(Color.Blue)
        )
    }
}


@Composable
fun GraphicLayerClip(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    clip = true,
                    shape = RoundedCornerShape(10.dp)
                )
                .background(Color.Blue)
        )
    }
}

@Composable
fun GraphicLayerCameraDistance(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    rotationY = 30f,
                    cameraDistance = 1.5f
                )
                .background(Color.Blue)
        )
    }
}

@Composable
fun GraphicLayerFlip(
    modifier: Modifier = Modifier
) {
    var flipped by remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .graphicsLayer(
                    rotationY = if (flipped) 180f else 60f,
                    cameraDistance = 8f,
                )
                .background(if (flipped) Color.Red else Color.Green)
                .clickable {
                    flipped = !flipped
                }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun GraphicLayerBlur(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(
                    renderEffect = BlurEffect(
                        radiusX = 50f,
                        radiusY = 50f,
                        edgeTreatment = TileMode.Decal
                    )
                )
                .background(Color.Blue)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun GraphicLayerColorFilter(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(

                )
                .background(Color.Blue)
        )
    }
}