package com.nameisjayant.androidpractise.ui.performance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.withContext


@OptIn(FlowPreview::class)
@Composable
fun SearchLazyColumn(
    modifier: Modifier = Modifier
) {
    var search by remember {
        mutableStateOf("")
    }
    var filteredList by remember { mutableStateOf(animalList) }

    LaunchedEffect(Unit) {
        snapshotFlow { search }
            .debounce(400)
            .collectLatest {
                val result = if (search.isBlank()) animalList
                else withContext(Dispatchers.Default) {
                    animalList.filter {
                        it.contains(search, ignoreCase = true)
                    }
                }
                filteredList = result
            }
    }



    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = search,
                onValueChange = {
                    search = it
                },
                placeholder = { Text("Search animals") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
            )
        }
        items(filteredList, key = { it }) {
            Text(
                it, style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            )
        }
    }

}


val animalList = listOf(
    "Dog",
    "Cat",
    "Elephant",
    "Lion",
    "Tiger",
    "Bear",
    "Giraffe",
    "Zebra",
    "Monkey",
    "Panda",
    "Kangaroo",
    "Deer",
    "Horse",
    "Rabbit",
    "Fox",
    "Wolf",
    "Sheep",
    "Goat",
    "Cow",
    "Buffalo"
)