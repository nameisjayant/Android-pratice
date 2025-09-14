package com.nameisjayant.androidpractise.ui.customShapes

// File: FourWaveTopShape.kt
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.toSize

/**
 * A Shape that draws [waveCount] smooth waves across the top edge of the rectangle.
 *
 * @param waveAmplitudeDp how deep the wave dips from the top in dp (positive -> dips downward).
 * @param waveCount number of full waves along the top edge (default 4).
 */
class FourWaveTopShape(
    private val waveAmplitudeDp: Dp = 20.dp,
    private val waveCount: Int = 4
) : Shape {

    init {
        require(waveCount >= 1) { "waveCount must be at least 1" }
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // convert amplitude from dp -> px
        val amplitudePx = with(density) { waveAmplitudeDp.toPx() }

        val path = Path().apply {
            // start at top-left corner
            moveTo(0f, 0f)

            // compute width per wave
            val waveWidth = size.width / waveCount.toFloat()

            // draw each wave using cubic Bézier (smooth hump)
            // each wave goes from y=0 -> down to y=amplitude -> back to y=0
            for (i in 0 until waveCount) {
                val startX = i * waveWidth
                val endX = startX + waveWidth
                val control1X = startX + waveWidth * 0.25f
                val control2X = startX + waveWidth * 0.75f

                // control points have y = amplitude to pull the curve downward
                cubicTo(
                    control1X, amplitudePx,
                    control2X, amplitudePx,
                    endX, 0f
                )
            }

            // down the right side, across the bottom, and back up left side
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        return Outline.Generic(path)
    }
}

/* ---------------------------
   Example usage in Composables
   --------------------------- */

@Composable
fun WaveTopCardExample() {
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        // Box clipped to wave shape
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(FourWaveTopShape(waveAmplitudeDp = 24.dp, waveCount = 4))
                .background(Color(0xFF4CAF50)) // green background clipped by the shape
        ) {
            // content inside clipped area
            Text(
                text = "Four-wave top shape",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
