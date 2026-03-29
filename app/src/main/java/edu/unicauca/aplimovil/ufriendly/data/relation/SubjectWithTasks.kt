package edu.unicauca.aplimovil.ufriendly.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task

//A subject can have multiple tasks
data class SubjectWithTasks(
    @Embedded
    val subject: Subject,
    @Relation(
        parentColumn = "id",
        entityColumn = "subjectId")
    val tasks: List<Task>
)
