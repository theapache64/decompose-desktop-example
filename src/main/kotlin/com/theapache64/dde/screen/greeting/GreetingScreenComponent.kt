package com.theapache64.dde.screen.greeting

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.theapache64.dde.navigation.Component

class GreetingScreenComponent(
    private val componentContext: ComponentContext,
    private val name: String,
    private val onGoBackClicked: () -> Unit
) : Component, ComponentContext by componentContext {

    companion object {
        private val greetings = listOf(
            "Bonjour",
            "Hola",
            "Olá",
            "Ciao",
            "Hi",
            "Hallo",
            "Hey"
        )
    }

    @Composable
    override fun render() {
        GreetingScreen(
            greeting = "${greetings.random()}, $name",
            onGoBackClicked = onGoBackClicked
        )
    }
}

