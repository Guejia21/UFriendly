package edu.unicauca.aplimovil.ufriendly.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import edu.unicauca.aplimovil.ufriendly.data.entity.ClassSchedule
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithGrades
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassScheduleDao {
    @Insert
    suspend fun insert(item: ClassSchedule)

    @Update
    suspend fun update(item: ClassSchedule)

    @Delete
    suspend fun delete(item: ClassSchedule)

}



