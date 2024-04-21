package com.example.yourphysicsfaculty.feature.bookmarks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.core.designSystem.component.NewsResourceCard
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme
import com.example.yourphysicsfaculty.core.model.NewsResource

@Composable
fun BookmarksScreen(
    viewModel: BookmarksViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val news = viewModel.feedState.collectAsStateWithLifecycle()
    BookmarksScreenContent(modifier = modifier, news = news.value, onToggleBookmark = viewModel::toggleBookmarked, onExpandedCardClick = {})
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarksScreenContent(
    news: List<NewsResource>,
    onExpandedCardClick: () -> Unit,
    onToggleBookmark: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = news,
            key = { it.id },
            contentType = { "newsFeedItem" },
        ) { userNewsResource ->
            NewsResourceCard(
                userNewsResource = userNewsResource,
                isBookmarked = userNewsResource.isBookmarked,
                onClick = {
                    onExpandedCardClick()
                },
                onToggleBookmark = { onToggleBookmark(userNewsResource.id, userNewsResource.isBookmarked) },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .animateItemPlacement(),
            )
        }
    }
}

@Preview
@Composable
fun ForYouScreenPreview(modifier: Modifier = Modifier) {
    YPFTheme {
        //BookmarksScreenContent()
    }
}