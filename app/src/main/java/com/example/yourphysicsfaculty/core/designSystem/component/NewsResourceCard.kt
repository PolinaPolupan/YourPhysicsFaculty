package com.example.yourphysicsfaculty.core.designSystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme
import com.example.yourphysicsfaculty.core.model.Topic
import com.example.yourphysicsfaculty.core.model.UserNewsResource
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsResourceCard(
    userNewsResource: UserNewsResource,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    ) {
        Column {
            if (!userNewsResource.headerImageUrl.isNullOrEmpty()) {
                Row {
                    NewsResourceHeaderImage(userNewsResource.headerImageUrl)
                }
            }
            Box(
                modifier = Modifier.padding(16.dp),
            ) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        NewsResourceTitle(
                            userNewsResource.title,
                            modifier = Modifier.fillMaxWidth((.8f)),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        BookmarkButton(isBookmarked, onToggleBookmark)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = userNewsResource.publishDate, style = MaterialTheme.typography.labelSmall)
                    Spacer(modifier = Modifier.height(12.dp))
                    NewsResourceShortDescription(userNewsResource.content)
                    Spacer(modifier = Modifier.height(12.dp))
                    NewsResourceTopics(
                        topics = userNewsResource.topics
                    )
                }
            }
        }
    }
}

@Composable
fun NewsResourceTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier,
) {
    Text(newsResourceTitle, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}

@Composable
fun BookmarkButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    YPFIconToggleButton(
        checked = isBookmarked,
        onCheckedChange = { onClick() },
        modifier = modifier,
        icon = {
            Icon(
                imageVector = YPFIcons.BookmarksBorder,
                contentDescription = stringResource(R.string.bookmarks_toggle_button),
            )
        },
    ) {
        Icon(
            imageVector = YPFIcons.Bookmarks,
            contentDescription = stringResource(R.string.bookmarks_toggle_button),
        )
    }
}

@Composable
fun NewsResourceHeaderImage(
    headerImageUrl: String?,
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = headerImageUrl,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            // Display a progress bar while loading
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) {
                imageLoader
            } else {
                painterResource(R.drawable.sign_fizfak_official)
            },
            contentDescription = null,
        )
    }
}

@Composable
fun NewsResourceShortDescription(
    newsResourceShortDescription: String,
) {
    Text(newsResourceShortDescription, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun NewsResourceTopics(
    topics: List<Topic>,
    modifier: Modifier = Modifier,
) {
    Row(
        // causes narrow chips
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        for (followableTopic in topics) {
            YPFTopicTag(
                onClick = { /*TODO*/ },
                enabled = true,
                text = { Text(text = followableTopic.name.uppercase(Locale.getDefault())) }
            )
        }
    }
}

@Preview("NewsResourceCardExpanded")
@Composable
private fun ExpandedNewsResourcePreview(
    @PreviewParameter(UserNewsResourcePreviewParameterProvider::class)
    userNewsResources: List<UserNewsResource>,
) {
    YPFTheme {
        NewsResourceCard(
            userNewsResource = userNewsResources[0],
            isBookmarked = true,
            onToggleBookmark = {},
            onClick = {}
        )
    }
}