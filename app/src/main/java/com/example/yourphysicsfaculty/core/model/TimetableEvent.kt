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
    val name: String,
    val type: TimetableEventType,
    val startTime: String,
    val endTime: String,
    val lecturers: List<Lecturer>,
    val groups: List<Group>,
    val room: String
)