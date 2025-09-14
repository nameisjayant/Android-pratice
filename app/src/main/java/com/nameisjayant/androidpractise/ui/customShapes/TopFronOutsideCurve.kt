package com.nameisjayant.androidpractise.ui.customShapes

// File: ProtrudingCupTopExample.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign

/**
 * Shape that creates a smooth cup/bowl that **protrudes above** the top edge (uses negative Y).
 */
class ProtrudingCupTopShape(
    private val cupWidthDp: Dp = 160.dp,
    private val cupHeightDp: Dp = 48.dp,
    private val curvatureFactor: Float = 0.7f // 0..1
) : Shape {
    init {
        require(curvatureFactor in 0f..1f) { "curvatureFactor must be between 0f and 1f" }
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cupWidthPx = with(density) { cupWidthDp.toPx() }.coerceAtMost(size.width)
        val cupHeightPx = with(density) { cupHeightDp.toPx() }

        val half = cupWidthPx / 2f
        val centerX = size.width / 2f
        val leftX = centerX - half
        val rightX = centerX + half

        // control offset controls the roundness of the bowl
        val controlOffset = half * (0.25f + 0.75f * curvatureFactor)

        val path = Path().apply {
            // top-left (0,0)
            moveTo(0f, 0f)

            // go to left edge of cup top
            lineTo(leftX, 0f)

            // left -> center (bulge upward using negative Y)
            cubicTo(
                leftX + controlOffset, 0f,
                centerX - controlOffset, -cupHeightPx,
                centerX, -cupHeightPx
            )

            // center -> right
            cubicTo(
                centerX + controlOffset, -cupHeightPx,
                rightX - controlOffset, 0f,
                rightX, 0f
            )

            // finish top edge and the rest of the rectangle
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        return Outline.Generic(path)
    }
}

@Composable
fun ProtrudingCupTopDemo() {
    // adjust these two to control look
    val cupWidth = 160.dp
    val cupHeight = 48.dp

    // outer container must be taller than the clipped box so the bulge can be seen
    Box(
        modifier = Modifier
            .fillMaxSize() // give room above for the bulge
            .background(Color(0xFFF3F4F6)),
        contentAlignment = Alignment.Center
    ) {
        // the "card" with the cup-top. We offset it upward by cupHeight so bulge shows outside the top edge.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .offset(y = -cupHeight) // <-- use foundation.layout.offset (Dp)
                .clip(ProtrudingCupTopShape(cupWidthDp = cupWidth, cupHeightDp = cupHeight, curvatureFactor = 0.85f))
                .background(Color(0xFF0D47A1))
        ) {
            Text(
                text = "Protruding cup top",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center).padding(12.dp)
            )
        }
    }
}