package com.theapache64.dde


import androidx.compose.runtime.remember
import androidx.compose.ui.window.singleWindowApplication
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.theapache64.dde.navigation.NavHostComponent
import com.theapache64.dde.theme.DecomposeDesktopExampleTheme

/**
 * Where all magic starts ;)
 */
fun main() = singleWindowApplication(
    title = "Decompose Desktop Example"
) {
    DecomposeDesktopExampleTheme {
        remember {
            DefaultComponentContext(
                LifecycleRegistry()
            ).let(::NavHostComponent)
        }
            .render()
    }
}