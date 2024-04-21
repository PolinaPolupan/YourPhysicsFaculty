package com.example.yourphysicsfaculty.core.designSystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme
import com.example.yourphysicsfaculty.core.model.Lecturer
import com.example.yourphysicsfaculty.core.model.TimetableEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableEventCard(
    event: TimetableEvent,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var visible by rememberSaveable { mutableStateOf(false) }
    Card(
        onClick = {
            visible = !visible
            onClick()
        },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            TimeText(startTime = event.startTime, endTime = event.endTime)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Text(
                        text = event.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.fillMaxWidth((.8f))
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(12.dp))

                RoomsText(name = event.room)
                LecturersText(lecturers = event.lecturers)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun TimeText(
    startTime: String,
    endTime: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.height(100.dp) ){
        Divider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxHeight()
                .width(7.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 8.dp)
        ) {
            Text(text = startTime, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
            Text(text = endTime, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
        }
    }
}

@Composable
fun RoomsText(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(imageVector = Icons.Filled.Map, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = name, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun LecturersText(
    lecturers: List<Lecturer>,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(imageVector = Icons.Filled.School, contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = lecturersString(lecturers), style = MaterialTheme.typography.labelLarge)
    }
}

@Preview
@Composable
fun TimetableEventCardPreview(
    @PreviewParameter(TimetableEventsPreviewParameterProvider::class)
    events: List<TimetableEvent>
) {
    YPFTheme {
        TimetableEventCard(
            event = events[0],
            isBookmarked = true,
            onToggleBookmark = { /*TODO*/ },
            onClick = { /*TODO*/ })
    }
}

fun lecturersString(lecturers: List<Lecturer>) : String {
    var lecturersString = ""
    for (lecturer in lecturers) {
        lecturersString += lecturer.name + ", "
    }
    lecturersString = lecturersString.substring(0, lecturersString.length - 2)
    return lecturersString
}