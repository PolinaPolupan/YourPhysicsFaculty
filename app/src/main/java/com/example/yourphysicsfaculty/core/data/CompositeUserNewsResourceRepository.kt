package com.example.yourphysicsfaculty.core.data

import com.example.yourphysicsfaculty.core.data.local.NewsResourcesDao
import com.example.yourphysicsfaculty.core.data.local.toExternal
import com.example.yourphysicsfaculty.core.data.local.toLocal
import com.example.yourphysicsfaculty.core.data.di.DefaultDispatcher
import com.example.yourphysicsfaculty.core.model.NewsResource
import com.example.yourphysicsfaculty.core.data.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implements a [UserNewsResourceRepository] by combining a [NewsRepository] with a
 * [UserDataRepository].
 */
class DefaultUserNewsResourceRepository @Inject constructor(
    private val localDataSource: NewsResourcesDao,
    private val networkDataSource: NetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : UserNewsResourceRepository {

    override fun observeAll(): Flow<List<NewsResource>> {
        return localDataSource.observeAll().map { it.toExternal() }
    }

    override suspend fun toggleBookmark(newsId: String, isBookmarked: Boolean) {
        localDataSource.updateBookmarked(newsId, isBookmarked)
        //refresh()
    }

    suspend fun refresh() {
        withContext(dispatcher) {
            val remoteNews = networkDataSource.getNewsResources()
            localDataSource.deleteAll()
            localDataSource.upsertAll(remoteNews.toLocal())
        }
    }

}
