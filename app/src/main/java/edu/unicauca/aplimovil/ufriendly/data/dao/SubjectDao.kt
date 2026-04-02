package edu.unicauca.aplimovil.ufriendly.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithGrades
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    //QUERIES
    @Query("SELECT * FROM subject WHERE id = :subjectId")
    fun getSubjectById(subjectId: Int): Flow<Subject>
    @Query("SELECT * FROM subject")
    fun getAllSubjects(): Flow<List<Subject>>

    @Transaction
    @Query("SELECT * FROM subject WHERE id = :subjectId")
    fun getSubjectWithTasks(subjectId: Int): Flow<SubjectWithTasks>

    @Transaction
    @Query("SELECT * FROM subject WHERE id = :subjectId")
    fun getSubjectWithGrades(subjectId: Int): Flow<SubjectWithGrades>

    @Transaction
    @Query("SELECT * FROM subject WHERE id = :subjectId")
    fun getSubjectWithSchedules(subjectId: Int): Flow<SubjectWithSchedules>

    @Transaction
    @Query("SELECT * FROM subject")
    fun getSubjectsWithSchedules(): Flow<List<SubjectWithSchedules>>

    //COMMANDS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject): Long

    @Update
    suspend fun updateSubject(subject: Subject)
    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Transaction
    @Query("SELECT id FROM subject ORDER BY id DESC LIMIT 1")
    fun getLastSubjectId(): Int
}



