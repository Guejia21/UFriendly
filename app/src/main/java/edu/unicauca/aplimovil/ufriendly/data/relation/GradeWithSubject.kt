package edu.unicauca.aplimovil.ufriendly.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject


data class GradeWithSubject (
    @Embedded
    val grade: Grade,

    @Relation(
        parentColumn = "subjectId",
        entityColumn = "id"
    )
    val subject: Subject? //se puede nulo ya que solo probamos que se almacenen los datos en bd de una calificacion
)