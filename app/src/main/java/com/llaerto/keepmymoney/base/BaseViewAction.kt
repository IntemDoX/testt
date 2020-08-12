package com.llaerto.keepmymoney.base

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

interface BaseViewAction

sealed class NavigationActions {
    data class NavigationAction(val destination: NavDirections, val navOptions: NavOptions? = null): NavigationActions()
    object PopBackStackAction: NavigationActions()
}
