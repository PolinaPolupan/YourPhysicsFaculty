package com.example.yourphysicsfaculty.feature.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourphysicsfaculty.core.data.UserNewsResourceRepository
import com.example.yourphysicsfaculty.core.data.local.NewsResourceEntity
import com.example.yourphysicsfaculty.core.data.local.NewsResourcesDao
import com.example.yourphysicsfaculty.core.data.local.toExternal
import com.example.yourphysicsfaculty.core.model.NewsResource
import com.example.yourphysicsfaculty.feature.foryou.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val localDataSource: NewsResourcesDao,
) : ViewModel() {


    var feedState: StateFlow<List<NewsResource>> =
        localDataSource.observeAllBookmarked()
            .map { it.toExternal() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = listOf(),
            )

    fun toggleBookmarked(newsId: String, isBookmarked: Boolean)
    {
        viewModelScope.launch {
            localDataSource.updateBookmarked(newsId, isBookmarked)
        }
    }
}