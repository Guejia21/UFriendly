package edu.unicauca.aplimovil.ufriendly.data.repository

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import edu.unicauca.aplimovil.ufriendly.data.dao.SubjectDao
import edu.unicauca.aplimovil.ufriendly.data.dao.TaskDao
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task
import edu.unicauca.aplimovil.ufriendly.notifications.TaskReminderWorker
import java.util.concurrent.TimeUnit

class TaskRepository(
    private val taskDao: TaskDao,
    private val context: Context
) {
    val allTasks = taskDao.getTasksWithSubjects()
    suspend fun insert(task: Task) {
        val id: Long = taskDao.insertTask(task)
        scheduleReminder(task.copy(id=id.toInt()))
    }
    suspend fun update(task: Task) {
        taskDao.updateTask(task)
        if (task.isDone) cancelReminder(task.id.toLong())
        else scheduleReminder(task)
    }
    suspend fun delete(task: Task){
        taskDao.deleteTask(task)
        cancelReminder(task.id.toLong())
    }

    suspend fun getTaskBySubject(subjectId:Int) = taskDao.getTasksBySubject(subjectId)
    suspend fun getTaskById(taskId:Int) = taskDao.getTaskById(taskId)
    suspend fun getTasksWithSubjects() = taskDao.getTasksWithSubjects()

    // ─── SCHEDULE ─────────────────────────────────────────────
    private fun scheduleReminder(task: Task) {
        val dueDate = task.dueDate ?: return   // null-safe: don't schedule if no date

        // TESTING: fire after 20 seconds instead of 24h before due date
        val delay = 20000L
        // PRODUCTION: uncomment this and remove the line above
        //val delay = (dueDate.time - TimeUnit.HOURS.toMillis(12)) - System.currentTimeMillis()
        if (delay <= 0) return

        val inputData = workDataOf(
            TaskReminderWorker.KEY_TASK_ID    to task.id,
            TaskReminderWorker.KEY_TASK_TITLE to task.name
        )

        val workRequest = OneTimeWorkRequestBuilder<TaskReminderWorker>()
            .setInitialDelay(delay, TimeUnit.MILLISECONDS)
            .setInputData(inputData)
            .addTag("reminder_${task.id}")   // tag lets us cancel by task ID
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "reminder_${task.id}",           // unique name prevents duplicates
                ExistingWorkPolicy.REPLACE,       // replaces if already scheduled
                workRequest
            )
    }

    // ─── CANCEL ───────────────────────────────────────────────
    private fun cancelReminder(taskId: Long) {
        WorkManager.getInstance(context)
            .cancelUniqueWork("reminder_${taskId}")
    }
}


