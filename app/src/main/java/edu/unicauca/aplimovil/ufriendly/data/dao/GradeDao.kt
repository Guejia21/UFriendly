package edu.unicauca.aplimovil.ufriendly.data.dao

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.relation.GradeWithSubject

@Dao
interface GradeDao {
    @Insert
    suspend fun insert(item: Grade)

    @Update
    suspend fun update(item: Grade)

    @Delete
    suspend fun delete(item: Grade)

    @Query("SELECT * FROM grades WHERE subjectId = :subjectId")
    fun getGradesBySubject(subjectId: Int): Flow<List<Grade>>

    @Query("SELECT * FROM grades")
    fun getAllGrades(): Flow<List<Grade>>

    @Transaction
    @Query("SELECT * FROM grades")
    fun getGradesWithSubject(): Flow<List<GradeWithSubject>>
}