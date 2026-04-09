package edu.unicauca.aplimovil.ufriendly.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import edu.unicauca.aplimovil.ufriendly.data.entity.ClassSchedule
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject

/**
 * Representa la información completa de una materia, incluyendo sus horarios y sus calificaciones.
 * Esta clase se utiliza para calcular dinámicamente el puntaje actual y el progreso.
 */
data class SubjectFullInfo(
    @Embedded
    val subject: Subject,
    
    @Relation(
        parentColumn = "id",
        entityColumn = "subjectId"
    )
    val classSchedules: List<ClassSchedule>,

    @Relation(
        parentColumn = "id",
        entityColumn = "subjectId"
    )
    val grades: List<Grade>
) {
    /**
     * Calcula la nota actual basada en las notas ingresadas y sus respectivos pesos.
     */
    val currentScore: Double
        get() = if (grades.isEmpty()) 0.0 else {
            grades.sumOf { it.value * it.weight }
        }

    /**
     * Calcula el porcentaje total evaluado de la materia (suma de los pesos de las notas).
     */
    val completionPercentage: Int
        get() = (grades.sumOf { it.weight } * 100).toInt().coerceAtMost(100)
}
