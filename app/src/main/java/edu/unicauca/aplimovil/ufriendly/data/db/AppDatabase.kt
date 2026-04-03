package edu.unicauca.aplimovil.ufriendly.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.unicauca.aplimovil.ufriendly.data.dao.ClassScheduleDao
import edu.unicauca.aplimovil.ufriendly.data.dao.SubjectDao
import edu.unicauca.aplimovil.ufriendly.data.dao.TaskDao
import edu.unicauca.aplimovil.ufriendly.data.dao.GradeDao
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.entity.ClassSchedule

@Database(
    entities = [
        Subject::class,
        Task::class,
        Grade::class,
        ClassSchedule::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    abstract fun taskDao(): TaskDao
    abstract fun gradeDao(): GradeDao

    abstract fun classScheduleDao(): ClassScheduleDao


    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database" // Database file name
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
}

}
