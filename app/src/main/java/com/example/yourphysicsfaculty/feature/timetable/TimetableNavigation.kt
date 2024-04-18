package com.example.yourphysicsfaculty.feature.timetable

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations

fun NavController.navigateToTimetable() = navigate(YPFDestinations.TIMETABLE_ROUTE)

fun NavGraphBuilder.timetableScreen(
) {
    composable(route = YPFDestinations.TIMETABLE_ROUTE) {
        TimetableScreen()
    }
}