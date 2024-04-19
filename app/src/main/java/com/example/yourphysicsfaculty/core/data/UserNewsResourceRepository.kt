package com.example.yourphysicsfaculty.core.data

import com.example.yourphysicsfaculty.core.model.NewsResource
import kotlinx.coroutines.flow.Flow

/**
 * Data layer implementation for [UserNewsResource]
 */
interface UserNewsResourceRepository {
    /**
     * Returns available news resources as a stream.
     */
    fun observeAll(): Flow<List<NewsResource>>

    //suspend fun toggleBookmark()
    /**
     * Returns available news resources as a stream.
     */
    /* suspend fun getAll(): List<UserNewsResource>

    /**
     * Returns the user's bookmarked news id resources.
     */
    suspend fun getAllBookmarkedIds(): List<String>*/
}