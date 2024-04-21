package com.example.yourphysicsfaculty.core.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yourphysicsfaculty.core.model.NewsResource
import com.example.yourphysicsfaculty.core.data.network.NetworkNewsResource

/**
 * Defines an NiA news resource.
 */
@Entity(
    tableName = "news_resources",
)
data class NewsResourceEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    @ColumnInfo(name = "header_image_url")
    val headerImageUrl: String?,
    @ColumnInfo(name = "publish_date")
    val publishDate: String,
    val type: String,
    val topics: List<String>,
    val isBookmarked: Boolean
)

fun NewsResourceEntity.asExternalModel() = NewsResource(
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


fun NewsResource.asLocal() = NewsResourceEntity(
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

fun NetworkNewsResource.asLocal(bookmarksIds: List<String>) = NewsResourceEntity(
    id = id,
    title = title,
    content = content,
    url = url,
    headerImageUrl = headerImageUrl,
    publishDate = publishDate,
    type = type,
    topics = topics,
    isBookmarked = id in bookmarksIds
)
// Note: JvmName is used to provide a unique name for each extension function with the same name.
// Without this, type erasure will cause compiler errors because these methods will have the same
// signature on the JVM.
@JvmName("localToExternal")
fun List<NewsResourceEntity>.toExternal() = map(NewsResourceEntity::asExternalModel)

@JvmName("localToLocal")
fun List<NewsResource>.toLocal() = map(NewsResource::asLocal)

@JvmName("localToLo")
fun List<NetworkNewsResource>.toLocal(bookmarksIds: List<String>) = map {it.asLocal(bookmarksIds)}