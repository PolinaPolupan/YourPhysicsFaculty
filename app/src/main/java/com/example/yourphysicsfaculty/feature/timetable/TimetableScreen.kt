package com.example.yourphysicsfaculty.feature.timetable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yourphysicsfaculty.core.designSystem.component.TimetableEventCard
import com.example.yourphysicsfaculty.core.designSystem.component.TimetableEventsPreviewParameterProvider
import com.example.yourphysicsfaculty.core.designSystem.component.YPFBackground
import com.example.yourphysicsfaculty.core.designSystem.theme.YPFTheme
import com.example.yourphysicsfaculty.core.model.TimetableEvent
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimetableScreen(
    viewModel: TimetableViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    TimetableScreenContent(events = uiState.value.events)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimetableScreenContent(
    events: List<TimetableEvent>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DatesRow()
        Spacer(modifier = Modifier.height(16.dp))
        EventsList(events = events)
    }
}

@Composable
fun EventsList(
    events: List<TimetableEvent>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items = events) {event ->
            TimetableEventCard(
                event = event,
                isBookmarked = true,
                onToggleBookmark = { /*TODO*/ },
                onClick = { /*TODO*/ })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatesRow(
    modifier: Modifier = Modifier
) {
    val dataSource = CalendarDataSource()
    // get CalendarUiModel from CalendarDataSource, and the lastSelectedDate is Today.
    var calendarUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }
    Column(modifier = modifier.fillMaxWidth()) {
        Header(
            data = calendarUiModel,
            onPrevClickListener = { startDate ->
                // refresh the CalendarUiModel with new data
                // by get data with new Start Date (which is the startDate-1 from the visibleDates)
                val finalStartDate = startDate.minusDays(1)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            },
            onNextClickListener = { endDate ->
                // refresh the CalendarUiModel with new data
                // by get data with new Start Date (which is the endDate+2 from the visibleDates)
                val finalStartDate = endDate.plusDays(2)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            }
        )
        Content(data = calendarUiModel, onDateClickListener = { date ->
            // refresh the CalendarUiModel with new data
            // by changing only the `selectedDate` with the date selected by User
            calendarUiModel = calendarUiModel.copy(
                selectedDate = date,
                visibleDates = calendarUiModel.visibleDates.map {
                    it.copy(
                        isSelected = it.date.isEqual(date.date)
                    )
                }
            )
        })
    }
}


@Composable
fun Header(
    data: CalendarUiModel,
    // calbacks to click previous & back button should be registered outside
    onPrevClickListener: (java.time.LocalDate) -> Unit,
    onNextClickListener: (java.time.LocalDate) -> Unit,
) {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            // show "Today" if user selects today's date
            // else, show the full format of the date
            text = if (data.selectedDate.isToday) {
                "Today"
            } else {
                data.selectedDate.date.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                )
            },
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = { // invoke previous callback when its button clicked
            onPrevClickListener(data.startDate.date) }) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = "Back"
            )
        }
        IconButton(onClick = { // invoke next callback when this button is clicked
            onNextClickListener(data.endDate.date) }) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Next"
            )
        }
    }
}

@Composable
fun Content(
    data: CalendarUiModel,
    // callback should be registered from outside
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = data.visibleDates) { date ->
            ContentItem(
                date = date,
                onClickListener = onDateClickListener
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable { // making the element clickable, by adding 'clickable' modifier
                onClickListener(date)
            }
        ,
        colors = CardDefaults.cardColors(
            // background colors of the selected date
            // and the non-selected date are different
            containerColor = if (date.isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.Transparent
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@Composable
fun ForYouScreenPreview(
    @PreviewParameter(TimetableEventsPreviewParameterProvider::class)
    events: List<TimetableEvent>
) {
    YPFTheme {
       TimetableScreenContent(events = events)
    }
}