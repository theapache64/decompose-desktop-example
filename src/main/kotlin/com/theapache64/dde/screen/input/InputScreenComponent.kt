package com.theapache64.dde.screen.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.theapache64.dde.navigation.Component

class InputScreenComponent(
    private val componentContext: ComponentContext,
    private val onGoClicked: (name: String) -> Unit
) : Component, ComponentContext by componentContext {

    private var state by mutableStateOf(Model())

    @Composable
    override fun render() {
        InputScreen(
            name = state.name,
            onGoClicked = onGoClicked,
            onTextChanged = {
                state = state.copy(name = it)
            }
        )
    }

    private data class Model(
        val name: String = ""
    )
}



