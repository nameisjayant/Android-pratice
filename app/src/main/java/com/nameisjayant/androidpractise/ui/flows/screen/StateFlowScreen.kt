package com.nameisjayant.androidpractise.ui.flows.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nameisjayant.androidpractise.ui.flows.StateFlowViewModel

@Composable
fun StateFlowScreen(
    modifier: Modifier = Modifier,
    viewModel: StateFlowViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text("Current State: $uiState")
        Button(
            onClick = { viewModel.updateState("New State Updated!") }
        ) {
            Text("Change Value")
        }
    }

}