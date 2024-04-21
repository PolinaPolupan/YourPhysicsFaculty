package com.example.yourphysicsfaculty.feature.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations
import com.example.yourphysicsfaculty.feature.timetable.TimetableScreen

fun NavController.navigateToProfile() = navigate(YPFDestinations.PROFILE_ROUTE)

fun NavGraphBuilder.profileScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = YPFDestinations.PROFILE_ROUTE) {
        ProfileScreen(onShowSnackbar = onShowSnackbar)
    }
}