package com.example.yourphysicsfaculty.feature.foryou

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations

fun NavController.navigateToForYou() = navigate(YPFDestinations.FOR_YOU_ROUTE)

fun NavGraphBuilder.forYouScreen(
) {
    composable(route = YPFDestinations.FOR_YOU_ROUTE) {
        ForYouScreen()
    }
}