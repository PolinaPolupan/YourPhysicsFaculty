package com.example.yourphysicsfaculty.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.os.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.yourphysicsfaculty.app.navigation.TopLevelDestination
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations.BOOKMARKS_ROUTE
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations.FOR_YOU_ROUTE
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations.TIMETABLE_ROUTE
import com.example.yourphysicsfaculty.feature.bookmarks.navigateToBookmarks
import com.example.yourphysicsfaculty.feature.foryou.navigateToForYou
import com.example.yourphysicsfaculty.feature.profile.navigateToProfile
import com.example.yourphysicsfaculty.feature.timetable.navigateToTimetable
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberYPFAppState(
    navController: NavHostController = rememberNavController(),
): YPFAppState {
    return remember(
        navController,
    ) {
        YPFAppState(
            navController
        )
    }
}

class YPFAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            FOR_YOU_ROUTE -> TopLevelDestination.FOR_YOU
            BOOKMARKS_ROUTE -> TopLevelDestination.BOOKMARKS
            TIMETABLE_ROUTE -> TopLevelDestination.TIMETABLE
            else -> null
        }

    /**
     * Map of top level destinations to be used in the TopBar. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        when (topLevelDestination) {
            TopLevelDestination.FOR_YOU -> navController.navigateToForYou()
            TopLevelDestination.BOOKMARKS -> navController.navigateToBookmarks()
            TopLevelDestination.TIMETABLE -> navController.navigateToTimetable()
            TopLevelDestination.PROFILE -> navController.navigateToProfile()
        }
    }
}

sealed class ViewState {
    object LoggedIn: ViewState() // hasLoggedIn = true
    object NotLoggedIn: ViewState() // hasLoggedIn = false
    object Loading: ViewState()
}