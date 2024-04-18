package com.example.yourphysicsfaculty.feature.foryou

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yourphysicsfaculty.core.model.UserNewsResource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class ForYouViewModel() : ViewModel() {
   // val feedState: StateFlow<NewsFeedUiState> = NewsFeedUiState()
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
        val feed: List<UserNewsResource>,
    ) : NewsFeedUiState
}