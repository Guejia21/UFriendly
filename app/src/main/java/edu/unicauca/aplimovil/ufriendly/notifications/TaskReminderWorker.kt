package edu.unicauca.aplimovil.ufriendly.notifications

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import edu.unicauca.aplimovil.ufriendly.R

class TaskReminderWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        // Read the data that was passed when scheduling
        val taskId    = inputData.getInt(KEY_TASK_ID, -1)
        val taskTitle = inputData.getString(KEY_TASK_TITLE) ?: return Result.failure()

        if (taskId == -1) return Result.failure()

        showNotification(taskId.toLong(), taskTitle)
        return Result.success()
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification(taskId: Long, title: String) {
        val notification = NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
            .setSmallIcon(R.drawable.app_icon) // use any icon you have
            .setContentTitle("Task due soon!")
            .setContentText("\"$title\" expires in 24 hours")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // taskId.toInt() is used as notification ID
        // so each task gets its own notification (not overwritten)
        NotificationManagerCompat.from(context)
            .notify(taskId.toInt(), notification)
    }

    companion object {
        const val KEY_TASK_ID    = "task_id"
        const val KEY_TASK_TITLE = "task_title"
    }
}