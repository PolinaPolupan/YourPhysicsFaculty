package com.example.yourphysicsfaculty.core.model

/**
 * External data layer representation of a fully populated YPF news resource
 */
data class NewsResource(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String?,
    val publishDate: String,
    val type: String,
    val topics: List<Topic>,
)