package com.theapache64.dde

import androidx.compose.desktop.Window
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.arkivanov.decompose.extensions.compose.jetbrains.rootComponent
import com.theapache64.dde.navigation.NavHostComponent
import com.theapache64.dde.theme.DecomposeDesktopExampleTheme

/**
 * Where all magic starts ;)
 */
fun main() = Window(
    title = "Decompose Desktop Example"
) {
    DecomposeDesktopExampleTheme {
        rememberRootComponent(factory = ::NavHostComponent)
            .render()
    }
}