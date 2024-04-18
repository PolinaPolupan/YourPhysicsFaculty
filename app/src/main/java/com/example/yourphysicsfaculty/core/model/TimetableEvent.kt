package com.example.yourphysicsfaculty.core.model

import com.example.yourphysicsfaculty.R
import java.util.Date

enum class TimetableEventType(val title: Int) {
    PRACTICE(R.string.practice_title),
    LABORATORY_WORK(R.string.laboratory_work_title),
    INDEPENDENT_WORK(R.string.independent_work_title),
    LECTURE(R.string.lecture_title)
}


data class TimetableEvent(
    val id: String,
    val type: TimetableEventType,
    val startTime: Date,
    val endTime: Date,
    val professors: List<Professor>,
    val groups: List<Group>,
    val cabinet: String
)