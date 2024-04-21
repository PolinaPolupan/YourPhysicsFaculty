package com.example.yourphysicsfaculty.feature.foryou

import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourphysicsfaculty.core.data.UserNewsResourceRepository
import com.example.yourphysicsfaculty.core.model.NewsResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val userNewsResourceRepository: UserNewsResourceRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            userNewsResourceRepository.refresh()
        }
    }

    var feedState: StateFlow<NewsFeedUiState> =
        userNewsResourceRepository.observeAll()
            .map(NewsFeedUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NewsFeedUiState.Loading,
            )

    fun toggleBookmarked(newsId: String, isBookmarked: Boolean)
    {
        viewModelScope.launch {
            userNewsResourceRepository.toggleBookmark(newsId, isBookmarked)
        }
    }
}



/**
 * A sealed hierarchy describing the state of the feed of news resources.
 */
sealed interface NewsFeedUiState {
    /**
     * The feed is still loading.
     */
    data object Loading : NewsFeedUiState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val feed: List<NewsResource>,
    ) : NewsFeedUiState
}