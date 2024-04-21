package com.example.yourphysicsfaculty.core.model

import java.time.LocalDate

data class DayEvents(
    val day: LocalDate,
    val events: List<TimetableEvent>
)
