package edu.unicauca.aplimovil.ufriendly.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    tableName = "grades",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"]
        )],
    indices = [Index("subjectId")]

)
data class Grade (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val value: Double,
    val weight: Double,
    val date: String,
    val subjectId: Int?,
)
