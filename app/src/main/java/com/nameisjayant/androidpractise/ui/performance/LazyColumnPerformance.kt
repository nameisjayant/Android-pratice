package com.nameisjayant.androidpractise.ui.performance

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun LazyColumnPerformance(
    modifier: Modifier = Modifier
) {
    val chatList = listOf("Text", "Image", "LongText", "Video")
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            items = chatList,
            key = { index, item ->
                index
            },
            contentType ={ index,item ->
                item // Text, Image , LongText , Video
            }
        ) { index, item ->
            when (item) {
                "Text" -> {
                    // Text Layout
                }

                "Image" -> {
                    // Image Layout
                }

                "LongText" -> {
                    // Long Text Layout
                }

                "Video" -> {
                    // Video Layout
                }
            }
        }
    }
}