package com.example.yourphysicsfaculty.core.network

import com.example.yourphysicsfaculty.core.model.Topic

/**
 * Network data layer representation of a fully populated YPF news resource
 */
data class NetworkNewsResource(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String?,
    val publishDate: String,
    val type: String,
    val topics: List<Topic>,
)