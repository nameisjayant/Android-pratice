package com.nameisjayant.androidpractise

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nameisjayant.androidpractise.ui.lazycolumnImplementations.ContactWithAlphabetSearchScreen
import com.nameisjayant.androidpractise.ui.lazycolumnImplementations.ParallaxEffectScreen
import com.nameisjayant.androidpractise.ui.performance.CreditCardScreen
import com.nameisjayant.androidpractise.ui.theme.AndroidPractiseTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPractiseTheme {
                Scaffold {
                    ContactWithAlphabetSearchScreen(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}

fun Modifier.gradientBackground(): Modifier {
    return this.background(
        Brush.verticalGradient(
            colors = listOf(Color.Blue, Color.Cyan)
        )
    )

}

fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = this.clickable(
    indication = null,
    interactionSource = MutableInteractionSource(),
    onClick = onClick
)

@Composable
fun NoRippleEffect(modifier: Modifier = Modifier) {
    Text(
        text = "Hello World", modifier = modifier
            .fillMaxWidth()
            .clickableWithoutRipple {

            }, style = TextStyle(
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun GradientBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground()
    )
}

@Composable
fun setStatusBarColor(
    modifier: Modifier = Modifier
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        LaunchedEffect(key1 = true) {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Red.toArgb()
            window.navigationBarColor = Color.Red.toArgb()
        }
    }


}

val LocalNavigator = staticCompositionLocalOf<NavHostController> {
    error("Unable to create navigation object")
}

@Composable
fun Modifier.shimmerEffect(): Modifier {
    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.3f),
        Color.Gray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateX = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return this.background(
        brush = Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = translateX.value, y = translateX.value),
            end = Offset(translateX.value + 300f, translateX.value + 300f)
        )
    )
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .defaultCornerRadius()
            .fillMaxWidth()
    ) {
    }
}

fun Modifier.defaultCornerRadius(): Modifier {
    return this.background(
        color = Color.Gray,
        shape = RoundedCornerShape(4.dp)
    )
}