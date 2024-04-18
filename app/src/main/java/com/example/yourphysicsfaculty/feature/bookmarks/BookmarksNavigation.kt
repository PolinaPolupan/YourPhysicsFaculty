package com.example.yourphysicsfaculty.feature.bookmarks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.yourphysicsfaculty.app.navigation.YPFDestinations.BOOKMARKS_ROUTE

fun NavController.navigateToBookmarks() = navigate(BOOKMARKS_ROUTE)

fun NavGraphBuilder.bookmarksScreen(
) {
    composable(route = BOOKMARKS_ROUTE) {
        BookmarksScreen()
    }
}