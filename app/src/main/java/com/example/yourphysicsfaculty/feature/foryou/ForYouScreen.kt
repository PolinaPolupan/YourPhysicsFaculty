package com.example.yourphysicsfaculty.feature.foryou

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.core.designSystem.component.NewsResourceCard
import com.example.yourphysicsfaculty.core.designSystem.component.UserNewsResourcePreviewParameterProvider
import com.example.yourphysicsfaculty.core.designSystem.component.YPFIcons
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme
import com.example.yourphysicsfaculty.core.model.NewsResource

@Composable
fun ForYouScreen(
    modifier: Modifier = Modifier,
    viewModel: ForYouViewModel = hiltViewModel(),
) {
    val feedState = viewModel.feedState.collectAsStateWithLifecycle()
    ForYouScreenContent(
        feedState = feedState.value,
        modifier = modifier
    )
}

@Composable
fun ForYouScreenContent(
    feedState: NewsFeedUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        newsFeed(feedState = feedState, onExpandedCardClick = {})
    }
}

/**
 * An extension on [LazyListScope] defining a feed with news resources.
 * Depending on the [feedState], this might emit no items.
 */
@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.newsFeed(
    feedState: NewsFeedUiState,
    onExpandedCardClick: () -> Unit = {},
) {
    when (feedState) {
        NewsFeedUiState.Loading -> Unit
        is NewsFeedUiState.Success -> {
            items(
                items = feedState.feed,
                key = { it.id },
                contentType = { "newsFeedItem" },
            ) { userNewsResource ->
               NewsResourceCard(
                    userNewsResource = userNewsResource,
                    isBookmarked = true,
                    onClick = {
                        onExpandedCardClick()
                    },
                    onToggleBookmark = {
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .animateItemPlacement(),
                )
            }
        }
    }
}

@Preview
@Composable
fun ForYouScreenPreview(
    @PreviewParameter(UserNewsResourcePreviewParameterProvider::class)
    userNewsResources: List<NewsResource>
) {
    YPFTheme {
        //ForYouScreenContent(feedState = NewsFeedUiState.Success(feed = userNewsResources))
    }
}