package com.example.yourphysicsfaculty.core.model

/**
 * Class summarizing user interest data
 */
data class UserData(
    val bookmarkedNewsResources: Set<String>,
    val viewedNewsResources: Set<String>,
    val shouldHideOnboarding: Boolean,
)