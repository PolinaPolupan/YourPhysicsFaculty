package com.example.yourphysicsfaculty.core.designSystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.Background.ktyourphysicsfaculty.core.designSystem.component.linearGradientScrim
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme

/**
 * The main background for the app.
 *
 * @param modifier Modifier to be applied to the background.
 * @param content The background content.
 */

@Composable
fun YPFBackground(
    alpha: Float = 0.5f,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.BottomCenter,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier
            .fillMaxSize()
            .linearGradientScrim(
                color = MaterialTheme.colorScheme.primary
                    .copy(alpha = alpha),
                start = Offset(100f, 0f),
                end = Offset(0f, 2000f),
                decay = 1f
            )/*
            .linearGradientScrim(
                color = MaterialTheme.colorScheme.primary
                    .saturation(6f)
                    .copy(alpha = 0.3f),
                startYPercentage = 1f,
                endYPercentage = 0f,
                start = Offset(0f, 0f),
                end = Offset(500f, 500f),
                decay = 3f
            )
            .linearGradientScrim(
                color = Color.Black
                    .copy(alpha = 0.6f),
                startYPercentage = 0f,
                endYPercentage = 1f,
                start = Offset(0f, 0f),
                end = Offset(0f, 700f),
                decay = 3f
            )*/
    ) {
        content()
    }
}

@Preview
@Composable
fun MyMusicGradientBackgroundPreview() {
    YPFTheme{
        YPFBackground {

        }
    }
}
