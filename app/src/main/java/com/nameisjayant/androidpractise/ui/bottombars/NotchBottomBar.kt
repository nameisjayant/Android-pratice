package com.nameisjayant.androidpractise.ui.bottombars

// File: PixelPerfectNotchedBottomBar.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import com.nameisjayant.androidpractise.R

/**
 * NotchedRoundedCornerShape draws a rounded rect with a smooth, shallow notch at top-center.
 * notchHalfWidth - how far notch extends horizontally from center (Dp)
 * notchDepth - how deep the notch goes down from top edge (Dp)
 * cornerRadius - main bar corner radius (Dp)
 */
class NotchedRoundedCornerShape(
    private val notchHalfWidth: Dp,
    private val notchDepth: Dp,
    private val cornerRadius: Dp
) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerPx = with(density) { cornerRadius.toPx() }
        val halfNotchPx = with(density) { notchHalfWidth.toPx() }
        val depthPx = with(density) { notchDepth.toPx() }

        val cx = size.width / 2f
        val top = 0f
        val bottom = size.height

        val path = Path()

        // Start at bottom-left (consider corner)
        path.moveTo(cornerPx, bottom)

        // bottom edge -> bottom-right
        path.lineTo(size.width - cornerPx, bottom)
        path.quadraticBezierTo(size.width, bottom, size.width, bottom - cornerPx)

        // right edge -> top-right
        path.lineTo(size.width, cornerPx)
        path.quadraticBezierTo(size.width, top, size.width - cornerPx, top)

        // top edge -> move to right edge of notch
        path.lineTo(cx + halfNotchPx, top)

        // Draw shallow, smooth concave notch using arcTo on a rect slightly taller than deep.
        // Using a flatter oval produces a smooth shallow dip.
        val arcRect = Rect(
            left = cx - halfNotchPx,
            top = top - depthPx * 0.9f,   // place rect slightly above so arc is shallow
            right = cx + halfNotchPx,
            bottom = top + depthPx * 1.1f // slightly taller to soften curvature
        )

        // sweep -180 degrees from right to left to create concave notch
        path.arcTo(rect = arcRect, startAngleDegrees = 0f, sweepAngleDegrees = -180f, forceMoveTo = false)

        // continue top edge left
        path.lineTo(cornerPx, top)
        path.quadraticBezierTo(0f, top, 0f, cornerPx)

        // left edge -> bottom-left
        path.lineTo(0f, bottom - cornerPx)
        path.quadraticBezierTo(0f, bottom, cornerPx, bottom)

        path.close()
        return Outline.Generic(path)
    }
}

/**
 * Improved notched bottom bar using your resources. Tweak the constants below for micro adjustments.
 */
@Composable
fun PixelPerfectNotchedBottomBar() {
    // Tunable sizes
    val barHeight = 64.dp
    val ringDiameter = 92.dp      // halo ring visible around notch (tune: 88..98)
    val centerDiameter = 64.dp    // purple button (tune: 60..68)
    val cornerRadius = 14.dp      // bar corner radius

    // Separate notch width vs depth so notch is fine and shallow
    val notchHalfWidth = (ringDiameter * 0.56f) / 2f   // slightly narrower than the ring width
    val notchDepth = ringDiameter * 0.18f             // shallow dip (smaller => finer)

    val items = listOf(
        BottomItem("Home", R.drawable.home),
        BottomItem("Search", R.drawable.search),
        BottomItem("Center", R.drawable.shop),
        BottomItem("Cart", R.drawable.cart),
        BottomItem("Profile", R.drawable.profile),
    )

    val purple = Color(0xFF6A46FF)
    val paleRing = Color(0xFFDCE7FF)
    val activeBlue = Color(0xFF2E6BFF)
    val inactiveGrey = Color(0xFF9FAEC8)
    val bg = Color(0xFFF6F7F9) // background behind bar (change to your screen bg)

    var selected by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight + (ringDiameter / 2f))
            .background(bg)
    ) {
        // 1) pale halo behind the bar so it shows through notch
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .size(ringDiameter)
//                .zIndex(0f)
//                .background(paleRing, shape = CircleShape)
//        )

        // 2) Main notched bar
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(barHeight)
                .padding(horizontal = 16.dp),
            shape = NotchedRoundedCornerShape(
                notchHalfWidth = notchHalfWidth,
                notchDepth = notchDepth,
                cornerRadius = cornerRadius
            ),
            color = Color.White,
            shadowElevation = 6.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    BottomNavItem(
                        items[0],
                        selected == 0,
                        { selected = 0 },
                        activeBlue,
                        inactiveGrey
                    )
                    BottomNavItem(
                        items[1],
                        selected == 1,
                        { selected = 1 },
                        activeBlue,
                        inactiveGrey
                    )
                }

                // Reserve center space equal to ring so items space evenly
                Spacer(modifier = Modifier.width(ringDiameter))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    BottomNavItem(
                        items[3],
                        selected == 3,
                        { selected = 3 },
                        activeBlue,
                        inactiveGrey
                    )
                    BottomNavItem(
                        items[4],
                        selected == 4,
                        { selected = 4 },
                        activeBlue,
                        inactiveGrey
                    )
                }
            }
        }

        // 3) pale ring drawn again above the bar so the top of the halo is visible
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .size(ringDiameter)
//                .zIndex(1f)
//                .background(paleRing, shape = CircleShape)
//        )

        // 4) Purple center button with soft shadow and better vertical alignment
        val centerOffsetY = (ringDiameter / 2f) - (centerDiameter / 15f) + 2.dp // small nudge to sit well in notch
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = centerOffsetY)
                .size(centerDiameter)
                .zIndex(2f)
                .shadow(elevation = 10.dp, shape = CircleShape) // softer shadow to avoid black dot
                .clip(CircleShape)
                .background(purple)
                .clickable { selected = 2 },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(items[2].icon),
                contentDescription = items[2].title,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    item: BottomItem,
    selected: Boolean,
    onClick: () -> Unit,
    activeColor: Color,
    inactiveColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(64.dp)
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.title,
            tint = if (selected) activeColor else inactiveColor,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (selected) {
            Text(text = item.title, fontSize = 12.sp, color = activeColor)
        } else {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
