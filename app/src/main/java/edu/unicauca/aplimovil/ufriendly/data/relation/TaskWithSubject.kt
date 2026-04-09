package edu.unicauca.aplimovil.ufriendly.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
//TODO CAMBIAR
//A subject can have multiple tasks
data class TaskWithSubject(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "id")
    val subject: Subject?
)
