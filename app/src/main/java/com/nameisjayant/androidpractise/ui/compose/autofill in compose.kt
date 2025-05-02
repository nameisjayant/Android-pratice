package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.platform.LocalAutofillManager
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp


@Composable
fun AutoFillScreen(modifier: Modifier = Modifier) {

    var username by remember { mutableStateOf("") }
    val autofillManager = LocalAutofillManager.current
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text("Enter username") },
                modifier = Modifier.semantics {
                    contentType = ContentType.NewPassword
                }
            )
            Button(onClick = {
                autofillManager?.commit()
            }) {
                Text("Submit")
            }
        }
    }
}

