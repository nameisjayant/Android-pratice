package com.nameisjayant.androidpractise.ui.bottombars

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nameisjayant.androidpractise.R

@Composable
fun FancyBottomBarExample() {
    val items = listOf(
        BottomItem("Home", R.drawable.home),
        BottomItem("Search", R.drawable.search),
        BottomItem("Center", R.drawable.shop),
        BottomItem("Cart", R.drawable.cart),
        BottomItem("Profile", R.drawable.profile),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    // Scaffold to host content + bottom bar
    Scaffold(
        containerColor = Color(0xFFF5F7FA),
        bottomBar = {
            Box {
                // Bottom background bar
                BottomBarBackground(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                )

                // Row of items - keep a placeholder slot for the center button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left two items
                    BottomNavItem(
                        item = items[0],
                        selected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 }
                    )
                    BottomNavItem(
                        item = items[1],
                        selected = selectedIndex == 1,
                        onClick = { selectedIndex = 1 }
                    )

                    // Placeholder to keep spacing for center button
                    Spacer(modifier = Modifier.width(centerButtonSpacing()))

                    // Right two items
                    BottomNavItem(
                        item = items[3],
                        selected = selectedIndex == 3,
                        onClick = { selectedIndex = 3 }
                    )
                    BottomNavItem(
                        item = items[4],
                        selected = selectedIndex == 4,
                        onClick = { selectedIndex = 4 }
                    )
                }

                // Center floating button overlay
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CenterFloatingButton(
                        onClick = { selectedIndex = 2 },
                        selected = selectedIndex == 2,
                        icon = items[2].icon,
                        size = 84.dp,
                        outerRingSize = 96.dp
                    )
                }
            }
        }
    ) { innerPadding ->
        // Main content placeholder
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .safeDrawingPadding()
            ,
            contentAlignment = Alignment.Center
        ) {
            Text("Content goes here", color = Color.DarkGray)
        }
    }
}

data class BottomItem(val title: String, @DrawableRes val icon: Int)

@Composable
private fun BottomBarBackground(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        tonalElevation = 2.dp,
        color = Color.White,
        shadowElevation = 4.dp
    ) {}
}

@Composable
private fun BottomNavItem(
    item: BottomItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    val activeColor = Color(0xFF2E6BFF) // blue for selected text/icon
    val inactiveColor = Color(0xFFBFC9D9) // greyish for unselected

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(68.dp)
            .height(72.dp)
            .padding(top = 8.dp)
            .wrapContentSize()
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.title,
            tint = if (selected) activeColor else inactiveColor,
            modifier = Modifier.size(22.dp)
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = item.title,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) activeColor else inactiveColor
        )
    }
}

@Composable
private fun CenterFloatingButton(
    onClick: () -> Unit,
    selected: Boolean,
    @DrawableRes icon: Int,
    size: Dp = 84.dp,
    outerRingSize: Dp = 96.dp
) {
    // Colors used in the design
    val purple = Color(0xFF6646FF)
    val ringBlue = Color(0xFFC9D6FF)
    val shadowColor = Color(0x44000000)

    // Outer ring
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .offset(y = (-28).dp) // raise above the bar
            .size(outerRingSize)
            .shadow(elevation = 0.dp, shape = CircleShape) // ring itself doesn't have heavy shadow
    ) {
        // outer ring circle
        Box(
            modifier = Modifier
                .size(outerRingSize)
                .background(color = ringBlue, shape = CircleShape)
        )

        // inner purple circle with stronger shadow
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(size)
                .shadow(
                    elevation = 8.dp,
                    ambientColor = shadowColor,
                    spotColor = shadowColor,
                    shape = CircleShape
                )
                .background(color = purple, shape = CircleShape)
                .clickable { onClick() }
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Center",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
private fun centerButtonSpacing(): Dp {
    // space to match the center FAB footprint so left/right items spread like the reference
    // Should be slightly larger than the size of CenterFloatingButton inner circle
    return 96.dp
}