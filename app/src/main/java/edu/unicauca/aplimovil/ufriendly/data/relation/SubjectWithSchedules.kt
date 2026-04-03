package edu.unicauca.aplimovil.ufriendly.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import edu.unicauca.aplimovil.ufriendly.data.entity.ClassSchedule
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task

//A subject can have multiple schedules
data class SubjectWithSchedules(
    @Embedded
    val subject: Subject,
    @Relation(
        parentColumn = "id",
        entityColumn = "subjectId")
    val classSchedules: List<ClassSchedule>
): SaveableItem {
    override fun isValid(): Boolean {
        return subject.isValid() && classSchedules.isNotEmpty() && classSchedules.all { it.isValid() }
    }
}
