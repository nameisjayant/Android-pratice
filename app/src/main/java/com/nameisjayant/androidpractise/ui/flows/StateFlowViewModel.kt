package com.nameisjayant.androidpractise.ui.flows

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateFlowViewModel : ViewModel() {
    private val _state: MutableStateFlow<String> = MutableStateFlow("Initial State")
    var state = _state.asStateFlow()
        private set

    fun updateState(newState: String) {
        _state.value = newState
    }

}