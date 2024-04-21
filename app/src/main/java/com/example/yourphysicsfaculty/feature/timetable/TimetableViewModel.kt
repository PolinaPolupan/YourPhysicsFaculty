package com.example.yourphysicsfaculty.feature.timetable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.yourphysicsfaculty.core.designSystem.component.PreviewParameterData
import com.example.yourphysicsfaculty.core.model.DayEvents
import com.example.yourphysicsfaculty.core.model.NewsResource
import com.example.yourphysicsfaculty.core.model.TimetableEvent
import com.example.yourphysicsfaculty.feature.foryou.NewsFeedUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import javax.inject.Inject


@HiltViewModel
class TimetableViewModel @Inject constructor(): ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    val uiState = mutableStateOf(TimetableUiState(date = LocalDate.now(), events = PreviewParameterData.timetableEvents))

}

data class TimetableUiState(
    val date: LocalDate,
    val events: List<TimetableEvent>
)

/* A sealed hierarchy describing the state of the feed of news resources.
*/
sealed interface EventsFeedUiState {
    /**
     * The feed is still loading.
     */
    data object Loading : EventsFeedUiState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val events: DayEvents,
    ) : EventsFeedUiState
}

@RequiresApi(Build.VERSION_CODES.O)
class CalendarDataSource {

    val today: LocalDate

        get() {
            return LocalDate.now()
        }


    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfWeek = startDate.with(DayOfWeek.MONDAY)
        val endDayOfWeek = firstDayOfWeek.plusDays(7)
        val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)
        return toUiModel(visibleDates, lastSelectedDate)
    }

    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate)
        return Stream.iterate(startDate) { date ->
            date.plusDays(/* daysToAdd = */ 1)
        }
            .limit(numOfDays)
            .collect(Collectors.toList())
    }

    private fun toUiModel(
        dateList: List<LocalDate>,
        lastSelectedDate: LocalDate
    ): CalendarUiModel {
        return CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate))
            },
        )
    }

    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = CalendarUiModel.Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
    )
}

data class CalendarUiModel(
    val selectedDate: Date, // the date selected by the User. by default is Today.
    val visibleDates: List<Date> // the dates shown on the screen
) {

    val startDate: Date = visibleDates.first() // the first of the visible dates
    val endDate: Date = visibleDates.last() // the last of the visible dates

    data class Date(
        val date: LocalDate,
        val isSelected: Boolean,
        val isToday: Boolean
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        val day: String = date.format(DateTimeFormatter.ofPattern("E")) // get the day by formatting the date
    }
}