package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


fun buttonAction(isLoggedIn: Boolean): () -> Unit {
    return if (isLoggedIn) {
        {
            print("dashboard")
        }
    } else {
        {
            print("log In")
        }
    }
}

@Composable
fun LogInScreen(modifier: Modifier = Modifier) {

    Box {
        Button(onClick = buttonAction(true)) {
            Text("LogIn Button")
        }
    }
}