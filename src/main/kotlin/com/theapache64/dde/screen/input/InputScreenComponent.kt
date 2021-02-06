package com.theapache64.dde.screen.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.theapache64.dde.navigation.Component

class InputScreenComponent(
    private val componentContext: ComponentContext,
    private val onGoClicked: (name: String) -> Unit
) : Component, ComponentContext by componentContext {

    private val state = mutableStateOf(State())

    @Composable
    override fun render() {
        InputScreen(
            name = state.value.name,
            onGoClicked = onGoClicked,
            onTextChanged = {
                state.value = state.value.copy(name = it)
            }
        )
    }

    private data class State(
        val name: String = ""
    )
}



