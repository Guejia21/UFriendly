package edu.unicauca.aplimovil.ufriendly.data.dao

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import edu.unicauca.aplimovil.ufriendly.data.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE subjectId = :subjectId")
    fun getTasksBySubject(subjectId: Int): Flow<List<Task>>
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>
    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)
    @Update
    suspend fun updateTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
}