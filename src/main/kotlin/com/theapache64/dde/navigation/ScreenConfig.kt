package com.theapache64.dde.navigation

import com.arkivanov.decompose.statekeeper.Parcelable

sealed class ScreenConfig : Parcelable {
    object Input : ScreenConfig()
    data class Greeting(val name: String) : ScreenConfig()
}