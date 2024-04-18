package com.example.yourphysicsfaculty.app.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.app.navigation.YPFScreens.BOOKMARKS_SCREEN
import com.example.yourphysicsfaculty.app.navigation.YPFScreens.FOR_YOU_SCREEN
import com.example.yourphysicsfaculty.app.navigation.YPFScreens.TIMETABLE_SCREEN
import com.example.yourphysicsfaculty.core.designSystem.component.YPFIcons

/**
 * Screens used in [YPFDestinations]
 */
private object YPFScreens {
    const val FOR_YOU_SCREEN = "for_you"
    const val BOOKMARKS_SCREEN = "bookmarks"
    const val TIMETABLE_SCREEN = "timetable"
}

/**
 * Destinations used in the [YPFActivity]
 */
object YPFDestinations {
    const val FOR_YOU_ROUTE = FOR_YOU_SCREEN
    const val BOOKMARKS_ROUTE = BOOKMARKS_SCREEN
    const val TIMETABLE_ROUTE = TIMETABLE_SCREEN
}

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    FOR_YOU(
        selectedIcon = YPFIcons.Upcoming,
        unselectedIcon = YPFIcons.UpcomingBorder,
        iconTextId = R.string.for_you_screen_title,
        titleTextId = R.string.for_you_screen_title,
    ),
    BOOKMARKS(
        selectedIcon = YPFIcons.Bookmarks,
        unselectedIcon = YPFIcons.BookmarksBorder,
        iconTextId = R.string.bookmarks_screen_title,
        titleTextId = R.string.bookmarks_screen_title,
    ),
    TIMETABLE(
        selectedIcon = YPFIcons.Timetable,
        unselectedIcon = YPFIcons.TimetableBorder,
        iconTextId = R.string.timetable_screen_title,
        titleTextId = R.string.timetable_screen_title,
    ),
}

