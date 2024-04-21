package com.example.yourphysicsfaculty.core.designSystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yourphysicsfaculty.R
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme



@Composable
fun ServiceCard(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.size(80.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text = stringResource(text))
    }
}

@Preview
@Composable
fun ServiceCardPreview() {
    YPFTheme {
        ServiceCard(text = R.string.floors_scheme)
    }
}