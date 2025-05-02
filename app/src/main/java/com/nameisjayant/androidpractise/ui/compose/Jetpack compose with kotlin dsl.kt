package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class InfoCardScope {
    var titleContent: @Composable (() -> Unit)? = null
    var descriptionContent: @Composable (() -> Unit)? = null

    fun title(content: @Composable () -> Unit) {
        titleContent = content
    }

    fun description(content: @Composable () -> Unit) {
        descriptionContent = content
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    InfoCard(
        modifier = modifier.fillMaxSize(),
    ) {
        title {
            Text("Jetpack Compose", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
        description {
            Text(
                "Build beautiful UIs using Kotlin DSL.",
                fontSize = 14.sp,
            )
        }
    }
}


@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    content: InfoCardScope.() -> Unit
) {
    val scope = remember { InfoCardScope() }.apply(content)
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        scope.titleContent?.invoke()
        scope.descriptionContent?.invoke()
    }
}