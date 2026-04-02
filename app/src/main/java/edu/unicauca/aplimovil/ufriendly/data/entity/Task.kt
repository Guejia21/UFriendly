package edu.unicauca.aplimovil.ufriendly.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
import java.sql.Date

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"])
    ],
    indices = [Index("subjectId")]

)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val dueDate: String,
    val isDone: Boolean = false,
    val subjectId: Int?
): SaveableItem{
    override fun isValid(): Boolean {
        return name.isNotBlank() && dueDate.isNotBlank()
    }
}