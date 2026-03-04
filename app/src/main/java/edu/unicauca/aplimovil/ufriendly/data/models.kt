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
    val color: Color
)