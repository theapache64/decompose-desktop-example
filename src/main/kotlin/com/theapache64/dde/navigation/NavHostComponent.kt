package com.theapache64.dde.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.essenty.parcelable.Parcelable
import com.theapache64.dde.screen.greeting.GreetingScreenComponent
import com.theapache64.dde.screen.input.InputScreenComponent
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push

/**
 * Navigator
 */
class NavHostComponent(
    componentContext: ComponentContext
) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<ScreenConfig>()
    private val stack = childStack(
        source = navigation,
        initialStack = { listOf(ScreenConfig.Input) },
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
        navigation.push(ScreenConfig.Greeting(name))
    }

    /**
     * Invoked when `GO BACK` button clicked (GreetingScreen)
     */
    private fun onGoBackClicked() {
        navigation.pop()
    }


    /**
     * Renders screen as per request
     */
    @OptIn(ExperimentalDecomposeApi::class)
    @Composable
    override fun render() {
        Children(stack){
            it.instance.render()
        }
    }

    private sealed class ScreenConfig : Parcelable {
        object Input : ScreenConfig()
        data class Greeting(val name: String) : ScreenConfig()
    }
}

