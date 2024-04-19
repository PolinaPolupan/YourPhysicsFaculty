package com.example.yourphysicsfaculty.core.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the news_resources table.
 */
@Dao
interface NewsResourcesDao {

    /**
     * Observes list of bookmarked news.
     *
     * @return all news.
     */
    @Query("SELECT * FROM news_resources")
    fun observeAll(): Flow<List<NewsResourceEntity>>

    /**
     * Observes list of ids of bookmarked news.
     *
     * @return all ids.
     */
    @Query("SELECT id FROM news_resources")
    fun getAllIds(): List<String>

    @Query("SELECT id FROM news_resources")
    fun observeAllIds(): Flow<List<String>>

    /**
     * Observes a single news resource.
     *
     * @param newsId the news id.
     * @return the news resource with newsId.
     */
    @Query("SELECT * FROM news_resources WHERE id = :newsId")
    fun observeById(newsId: String): Flow<NewsResourceEntity>

    /**
     * Insert or update a new in the database. If a task already exists, replace it.
     *
     * @param newsResource the news resource to be inserted or updated.
     */
    @Upsert
    suspend fun upsert(newsResource: NewsResourceEntity)

    /**
     * Update the complete status of a task
     *
     * @param newsId id of the task
     * @param bookmarked status to be updated
     */
    @Query("UPDATE news_resources SET isBookmarked = :bookmarked WHERE id = :newsId")
    suspend fun updateBookmarked(newsId: String, bookmarked: Boolean)

    /**
     * Delete a news resource by id.
     *
     * @return the number of news deleted. This should always be 1.
     */
    @Query("DELETE FROM news_resources WHERE id = :newsId")
    suspend fun deleteById(newsId: String): Int

    /**
     * Delete all news.
     */
    @Query("DELETE FROM news_resources")
    suspend fun deleteAll()

    @Upsert
    suspend fun upsertAll(news: List<NewsResourceEntity>)
}