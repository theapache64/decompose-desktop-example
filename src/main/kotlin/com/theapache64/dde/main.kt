package com.theapache64.dde

import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.theapache64.dde.navigation.NavHostComponent
import com.theapache64.dde.theme.DecomposeDesktopExampleTheme
import javax.swing.SwingUtilities

/**
 * Where all magic starts ;)
 */
@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val windowState = WindowState()
    val lifecycle = LifecycleRegistry()
    val root = runOnMainThreadBlocking {  NavHostComponent(DefaultComponentContext(lifecycle)) }

    singleWindowApplication(
        state = windowState,
        title = "Decompose Desktop Example",
    ) {
        LifecycleController(lifecycle, windowState)

        DecomposeDesktopExampleTheme {
            root.render()
        }
    }
}

private inline fun <T : Any> runOnMainThreadBlocking(crossinline block: () -> T): T {
    lateinit var result: T
    SwingUtilities.invokeAndWait { result = block() }
    return result
}
