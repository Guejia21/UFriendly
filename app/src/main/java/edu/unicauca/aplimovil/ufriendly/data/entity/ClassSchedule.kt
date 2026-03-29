package edu.unicauca.aplimovil.ufriendly.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "class_schedules",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"]
        )],
    indices = [Index("subjectId")]
)
data class ClassSchedule (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: String,
    val startHour: String,
    val endHour: String,
    val subjectId: Int
)