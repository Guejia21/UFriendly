package edu.unicauca.aplimovil.ufriendly.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import edu.unicauca.aplimovil.ufriendly.data.SaveableItem
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
): SaveableItem{
    override fun isValid(): Boolean {
        return name.isNotBlank() && value > 0.0 && weight > 0.0 && date.isNotBlank()
    }
}
