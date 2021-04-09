package com.theapache64.dde.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.pop
import com.arkivanov.decompose.push
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.theapache64.dde.screen.greeting.GreetingScreenComponent
import com.theapache64.dde.screen.input.InputScreenComponent

/**
 * Navigator
 */
class NavHostComponent(
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {

    private val router = router<ScreenConfig, Component>(
        initialConfiguration = ScreenConfig.Input,
        childFactory = ::createScreenComponent
    )

    /**
     * Factory function to create screen from given ScreenConfig
     */
    private fun createScreenComponent(
        screenConfig: ScreenConfig,
        componentContext: ComponentContext
    ): Component {
        return when (screenConfig) {

            is ScreenConfig.Input -> InputScreenComponent(
                componentContext,
                ::onGoClicked
            )

            is ScreenConfig.Greeting -> GreetingScreenComponent(
                componentContext,
                screenConfig.name,
                ::onGoBackClicked
            )
        }
    }


    /**
     * Invoked when `GO` button clicked (InputScreen)
     */
    private fun onGoClicked(name: String) {
        router.push(ScreenConfig.Greeting(name))
    }

    /**
     * Invoked when `GO BACK` button clicked (GreetingScreen)
     */
    private fun onGoBackClicked() {
        router.pop()
    }


    /**
     * Renders screen as per request
     */
    @Composable
    override fun render() {
        Children(routerState = router.state) {
            it.instance.render()
        }
    }

    private sealed class ScreenConfig : Parcelable {
        object Input : ScreenConfig()
        data class Greeting(val name: String) : ScreenConfig()
    }
}

