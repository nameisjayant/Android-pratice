package com.nameisjayant.androidpractise.ui.sideeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun SideEffectScreen(
    modifier: Modifier = Modifier
) {
    val userName by produceState(initialValue = "Loading...") {
        value = fetchUserNameFromApi() // Fetching API data
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User: $userName", style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            )
        )
    }
}

suspend fun fetchUserNameFromApi(): String {
    delay(4000) // Simulating network delay
    return "John Doe" // Example user name
}