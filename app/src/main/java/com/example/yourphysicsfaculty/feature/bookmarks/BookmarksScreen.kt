package com.example.yourphysicsfaculty.feature.bookmarks

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme

@Composable
fun BookmarksScreen(
    modifier: Modifier = Modifier
) {
    BookmarksScreenContent(modifier = modifier)
}

@Composable
fun BookmarksScreenContent(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {

    }
}

@Preview
@Composable
fun ForYouScreenPreview(modifier: Modifier = Modifier) {
    YPFTheme {
        BookmarksScreenContent()
    }
}