package com.nameisjayant.androidpractise.ui.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TableHeader(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    headers: List<String>
) {
    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .background(Color.Gray)
            .padding(8.dp)
    ) {
        headers.forEach { header ->
            Text(
                text = header,
                modifier = Modifier
                    .width(100.dp)
                    .padding(8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TableContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    data: List<List<String>>
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(data) { row ->
            Row(modifier = Modifier.horizontalScroll(scrollState)) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        modifier = Modifier
                            .width(100.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun ScrollableTable(modifier: Modifier, headers: List<String>, data: List<List<String>>) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize()) {

        TableHeader(scrollState = scrollState, headers = headers)
        TableContent(scrollState = scrollState, data = data)

    }
}

@Composable
fun PreviewScrollableTable(
    modifier: Modifier = Modifier
) {
    val headers = listOf("ID", "Name", "Age", "City", "Country", "Status")
    val data = List(25) { index ->
        listOf(
            (index + 1).toString(),
            "User $index",
            (20 + index).toString(),
            "City $index",
            "Country $index",
            if (index % 2 == 0) "Active" else "Inactive"
        )
    }

    ScrollableTable(modifier, headers, data)
}