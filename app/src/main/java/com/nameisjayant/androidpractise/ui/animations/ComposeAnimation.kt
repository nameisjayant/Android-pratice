package com.nameisjayant.androidpractise.ui.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun AnimationAsDpScreen(
    modifier: Modifier = Modifier
) {
    var isExpand by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (isExpand) 150.dp else 50.dp,
        label = "",
        animationSpec = tween(500)
    )
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Spacer(modifier = Modifier
            .size(size)
            .background(Color.Red)
            .clickable {
                isExpand = !isExpand
            })
    }
}

@Composable
fun AnimationAsColorScreen(
    modifier: Modifier = Modifier
) {

    var isExpand by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (isExpand) Color.Blue else Color.Red,
        label = "",
        animationSpec = tween(500)
    )
    val size by animateDpAsState(
        targetValue = if (isExpand) 150.dp else 50.dp,
        label = "",
        animationSpec = tween(500)
    )
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Spacer(modifier = Modifier
            .size(size)
            .background(color)
            .clickable {
                isExpand = !isExpand
            })
    }
}

@Composable
fun TransitionAnimationScreen(
    modifier: Modifier = Modifier
) {
    var isExpand by remember {
        mutableStateOf(false)
    }
    val transition = updateTransition(targetState = isExpand, label = "")
    val color by transition.animateColor(label = "") {
        if (it) Color.Blue else Color.Red
    }
    val size by transition.animateDp(label = "") {
        if (it) 150.dp else 50.dp
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Spacer(modifier = Modifier
            .size(size)
            .background(color)
            .clickable {
                isExpand = !isExpand
            })
    }
}

@Composable
fun KeyframeAnimationScreen(
    modifier: Modifier = Modifier
) {
    var isExpand by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (isExpand) 150.dp else 50.dp,
        label = "",
        animationSpec = keyframes {
            durationMillis = 1000
            50.dp to 0
            120.dp to 500
            150 to 1000
        }
    )
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Spacer(modifier = Modifier
            .size(size)
            .background(Color.Red)
            .clickable {
                isExpand = !isExpand
            })
    }

}

@Composable
fun AnimationSpecsScreen(
    modifier: Modifier = Modifier
) {
    val tweenAnimation = tween<Dp>(
        durationMillis = 1000,
        delayMillis = 100,
        easing = LinearEasing
    )

    val springAnimation = spring<Dp>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessMediumLow
    )
    val keyframeAnimation = keyframes<Dp> {
        durationMillis = 1000
        50.dp to 100
        120.dp to 500
        150.dp to 1000
    }

    val repeatableAnimation = repeatable(
        iterations = 3,
        animation = tweenAnimation,
        repeatMode = RepeatMode.Reverse
    )

    val infiniteAnimation = infiniteRepeatable(
        animation = tweenAnimation,
        repeatMode = RepeatMode.Restart
    )

    var isExpand by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (isExpand) 150.dp else 50.dp,
        label = "",
        animationSpec = tweenAnimation
    )
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Spacer(modifier = Modifier
            .size(size)
            .background(Color.Red)
            .clickable {
                isExpand = !isExpand
            })
    }

}