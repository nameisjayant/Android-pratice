package com.nameisjayant.androidpractise.ui.compose_navigation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nameisjayant.androidpractise.ui.compose_navigation.navigation.DetailScreen

@Composable
fun NavigationDetailScreen(
    modifier: Modifier = Modifier,
    data:DetailScreen
) {
    
    LazyColumn {
        item { 
            Text(text = data.data[0].name)
        }
    }
    
}