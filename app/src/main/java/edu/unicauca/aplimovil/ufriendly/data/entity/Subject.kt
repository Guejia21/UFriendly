package edu.unicauca.aplimovil.ufriendly.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val teacher: String,
    val score: Double = 0.0, // Se calcula a partir de las notas
    val completionPercentage: Int = 0,
    val color: String
)