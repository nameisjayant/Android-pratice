package com.nameisjayant.androidpractise.uitesting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.nameisjayant.androidpractise.R


@Composable
fun TestScreen(modifier: Modifier = Modifier) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Button(
            onClick = {}, modifier = Modifier
                .fillMaxWidth(0.5f)
//                .semantics {
//                    contentDescription = "Submit Button"
//                }
                .testTag("submit")
        ) {
            Text(text = stringResource(R.string.submit))
        }

        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Warning Icon"
        )
    }
}