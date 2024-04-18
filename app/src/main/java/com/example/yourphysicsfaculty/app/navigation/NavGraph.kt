package com.example.yourphysicsfaculty.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations.FOR_YOU_ROUTE
import com.example.yourphysicsfaculty.app.ui.YPFAppState
import com.example.yourphysicsfaculty.feature.bookmarks.bookmarksScreen
import com.example.yourphysicsfaculty.feature.foryou.forYouScreen
import com.example.yourphysicsfaculty.feature.timetable.timetableScreen

@Composable
fun YPFNavGraph(
    appState: YPFAppState,
    modifier: Modifier = Modifier,
    startDestination: String = FOR_YOU_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        forYouScreen()
        bookmarksScreen()
        timetableScreen()
    }
}