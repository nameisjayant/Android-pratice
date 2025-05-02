package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle


@Composable
fun BasicBuildAnnotated(modifier: Modifier = Modifier) {

    val text = buildAnnotatedString {
        append("This is ")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Bold")
        }
        append(" and ")
        withStyle(
            style = SpanStyle(
                fontStyle = FontStyle.Italic
            )
        ) {
            append("Italic")
        }
    }

    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(text)
    }
}

