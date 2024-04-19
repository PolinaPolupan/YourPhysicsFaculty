package com.example.yourphysicsfaculty.core.data.network

import com.example.yourphysicsfaculty.core.model.NewsResource
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
    val topics: List<String>,
    val isBookmarked: Boolean
)

fun NetworkNewsResource.toExternal() = NewsResource(
    id = id,
    title = title,
    content = content,
    url = url,
    headerImageUrl = headerImageUrl,
    publishDate = publishDate,
    type = type,
    topics = topics,
    isBookmarked = isBookmarked
)