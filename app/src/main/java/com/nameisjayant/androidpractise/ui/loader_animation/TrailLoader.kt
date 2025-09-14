package com.nameisjayant.androidpractise.ui.loader_animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun OrbitTrailWithCollapseAndSizeDecay(
    modifier: Modifier = Modifier,

    // --- Visuals ---
    bgColor: Color = Color.Black,
    squareColor: Color = Color(0xFF00C8FF),
    radiusFraction: Float = 0.35f,
    squareSizeFraction: Float = 0.0867f,
    cornerFraction: Float = 0.18f,

    // --- Motion & timing ---
    stopAngleDeg: Float = -90f,   // pause location
    moveMillis: Int = 1200,       // sweep duration for 360°
    holdMillis: Int = 300,        // pause duration
    collapseLeadMillis: Int = 250,// last part of move where trail collapses

    // --- Trail behaviour ---
    trailCount: Int = 6,
    trailGapDeg: Float = 12f,
    minAlpha: Float = 0.12f,

    // --- New: visual size decay ---
    sizeDecayPerStep: Float = 0.12f, // 0.12 = each step is 12% smaller than the previous
    minSizeFractionOfHead: Float = 0.35f // clamp so tiny tails don’t vanish
) {
    val cycleMillis = (moveMillis + holdMillis).coerceAtLeast(1)
    val progress = rememberInfiniteTransition(label = "cycle")
        .animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = cycleMillis, easing = LinearEasing)
            ),
            label = "progress"
        )

    val tMs = progress.value * cycleMillis
    val moveEnd = moveMillis.toFloat()
    val collapseStart = (moveMillis - collapseLeadMillis).coerceAtLeast(0).toFloat()

    val phase = when {
        tMs < collapseStart -> 0 // move
        tMs < moveEnd       -> 1 // collapse
        else                -> 2 // hold
    }

    val angle = when (phase) {
        0, 1 -> stopAngleDeg + 360f * (tMs / moveEnd)
        else -> stopAngleDeg
    }

    // Collapse progress 0..1
    val collapseP = when (phase) {
        1 -> ((tMs - collapseStart) / (moveEnd - collapseStart)).coerceIn(0f, 1f)
        else -> 0f
    }

    Canvas(
        modifier = modifier
            .background(bgColor)
            .aspectRatio(1f)
    ) {
        val d = size.minDimension
        val center = Offset(size.width / 2f, size.height / 2f)
        val r = d * radiusFraction
        val headBase = d * squareSizeFraction

        val count = when (phase) {
            0, 1 -> trailCount
            else -> 1 // hold: only head
        }

        repeat(count) { i ->
            // Angular separation shrinks during collapse so trail “fits into” the head
            val gap = if (i == 0) 0f else trailGapDeg * (1f - collapseP)
            val a = angle - i * gap
            val rad = Math.toRadians(a.toDouble())
            val cx = center.x + (r * cos(rad)).toFloat()
            val cy = center.y + (r * sin(rad)).toFloat()

            // --- Size decay: each step smaller than the previous one ---
            val linearScale = (1f - sizeDecayPerStep * i).coerceAtLeast(minSizeFractionOfHead)
            // Extra, subtle tuck-in during collapse
            val collapseScale = 1f - 0.05f * collapseP * (i / (count - 1f).coerceAtLeast(1f))
            val sz = headBase * linearScale * collapseScale

            // Alpha: quadratic trail fade + extra fade during collapse
            val t = if (count <= 1) 0f else i / (count - 1f)
            val baseAlpha = if (i == 0) 1f
            else (minAlpha + (1f - t * t) * (1f - minAlpha))
            val alpha = if (phase == 1 && i > 0) baseAlpha * (1f - collapseP) else baseAlpha

            drawRoundRect(
                color = squareColor.copy(alpha = alpha),
                topLeft = Offset(cx - sz / 2f, cy - sz / 2f),
                size = Size(sz, sz),
                cornerRadius = CornerRadius(50.dp.toPx(), 50.dp.toPx())
            )
        }
    }
}
