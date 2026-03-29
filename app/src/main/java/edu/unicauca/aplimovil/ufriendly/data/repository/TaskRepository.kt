package edu.unicauca.aplimovil.ufriendly.data.repository

import edu.unicauca.aplimovil.ufriendly.data.dao.SubjectDao
import edu.unicauca.aplimovil.ufriendly.data.dao.TaskDao
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import edu.unicauca.aplimovil.ufriendly.data.entity.Task

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks = taskDao.getAllTasks()
    suspend fun insert(task: Task) = taskDao.insertTask(task)
    suspend fun update(task: Task) = taskDao.updateTask(task)
    suspend fun delete(task: Task) = taskDao.deleteTask(task)

    suspend fun getTaskBySubject(subjectId:Int) = taskDao.getTasksBySubject(subjectId)
    suspend fun getTaskById(taskId:Int) = taskDao.getTaskById(taskId)

}


