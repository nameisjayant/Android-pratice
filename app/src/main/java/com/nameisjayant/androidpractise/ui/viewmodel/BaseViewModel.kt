package com.nameisjayant.androidpractise.ui.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlin.reflect.KProperty1

interface UiState
interface UiEvent
interface UiIntent

abstract class BaseViewModel<S : UiState, I : UiIntent, E : UiEvent>(initialState: S) {

    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    fun <T> select(selector: (S) -> T): Flow<T> =
        state.map(selector).distinctUntilChanged()

    fun <T> select(prop: KProperty1<S, T>): Flow<T> =
        state.map { prop.get(it) }.distinctUntilChanged()
}





