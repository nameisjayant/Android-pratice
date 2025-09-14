package com.nameisjayant.androidpractise.ui.customShapes

// File: CupTopShape.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * CupTopShape - cuts a smooth U-shaped "cup" curve at the top center of the rectangle.
 *
 * @param cupWidthDp how wide the cup is horizontally (dp).
 * @param cupDepthDp how deep the cup dips from the top edge (dp).
 * @param curvatureFactor controls how rounded the sides are (0f..1f). Lower -> steeper sides, higher -> softer curve.
 */
class CupTopShape(
    private val cupWidthDp: Dp = 100.dp,
    private val cupDepthDp: Dp = 30.dp,
    private val curvatureFactor: Float = 0.5f
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
        val cupDepthPx = with(density) { cupDepthDp.toPx() }.coerceAtMost(size.height)
        val half = cupWidthPx / 2f
        val centerX = size.width / 2f

        // control point offset determines how "rounded" the U is; use curvatureFactor to scale it
        // when curvatureFactor==1 -> wide smooth curve, when 0 -> triangular-ish
        val controlOffset = half * (0.3f + 0.7f * curvatureFactor) // between ~0.3*half .. 1.0*half

        val leftX = centerX - half
        val rightX = centerX + half

        val path = Path().apply {
            // start at top-left
            moveTo(0f, 0f)

            // draw line to left edge of the cup
            lineTo(leftX, 0f)

            // smooth U using two cubic béziers:
            // left top -> left control -> bottom apex -> right control -> right top
            // First cubic: leftX -> mid (centerX) using left control near leftX, bottom at cupDepthPx
            cubicTo(
                leftX + controlOffset, 0f,                  // control1
                centerX - controlOffset, cupDepthPx,        // control2
                centerX, cupDepthPx                         // end at center bottom of the cup
            )

            // Second cubic: center -> rightX using mirrored control points
            cubicTo(
                centerX + controlOffset, cupDepthPx,        // control1
                rightX - controlOffset, 0f,                 // control2
                rightX, 0f                                  // end at right edge of cup top
            )

            // continue to top-right corner and close the rectangle
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        return Outline.Generic(path)
    }
}

/* ---------- Example usage ---------- */

@Composable
fun CupTopExample() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(CupTopShape(cupWidthDp = 145.dp, cupDepthDp = 40.dp, curvatureFactor = 0.7f))
                .background(Color(0xFF6A1B9A)) // purple background clipped by the cup top
        ) {
            Text(
                text = "Cup-shaped top",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
