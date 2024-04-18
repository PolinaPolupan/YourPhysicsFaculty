package com.example.yourphysicsfaculty.core.designSystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme

@Composable
fun YPFTopicTag(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        val containerColor = MaterialTheme.colorScheme.primaryContainer

        TextButton(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.textButtonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor),
                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = YPFTagDefaults.DISABLED_TOPIC_TAG_CONTAINER_ALPHA,
                ),
            ),
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                text()
            }
        }
    }
}

@Preview
@Composable
fun TagPreview() {
    YPFTheme {
        YPFTopicTag(onClick = {}) {
            Text("Topic".uppercase())
        }
    }
}

object YPFTagDefaults {
    const val UNFOLLOWED_TOPIC_TAG_CONTAINER_ALPHA = 0.5f
    const val DISABLED_TOPIC_TAG_CONTAINER_ALPHA = 0.12f
}
