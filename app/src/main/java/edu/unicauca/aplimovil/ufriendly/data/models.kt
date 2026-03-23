package edu.unicauca.aplimovil.ufriendly.data

import androidx.compose.ui.graphics.Color
data class DashboardState(
    val userName: String,
    val pendingCount: Int,
    val doneCount: Int,
    val expiredCount: Int
)

data class Subject(
    val name: String,
    val classDates: List<String>,
    val teacher: String,
    val completionPercentage: Int,
    val partialScore: Double, //por ahora solo se quema
    val color: Color,
    val grades: List<Grade>?
)
data class Task(
    val name: String,
    val description: String,
    val dueDate: String,
    val isDone: Boolean,
    val subject: Subject
)

data class Grade(
    val name: String,
    val value: Double,
    val weight: Double,
    val date: String
)