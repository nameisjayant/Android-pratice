package com.nameisjayant.androidpractise.ui.compose_navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nameisjayant.androidpractise.ui.compose_navigation.navigation.PersonData

@Composable
fun NavigationDetailScreen(
    modifier: Modifier = Modifier,
    data: List<PersonData>
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(data) {
            Text(text = "Name: ${it.name}")
            Text(text = "Age: ${it.age}")
        }
    }
}