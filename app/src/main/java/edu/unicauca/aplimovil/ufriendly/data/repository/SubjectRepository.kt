package edu.unicauca.aplimovil.ufriendly.data.repository

import edu.unicauca.aplimovil.ufriendly.data.dao.ClassScheduleDao
import edu.unicauca.aplimovil.ufriendly.data.dao.SubjectDao
import edu.unicauca.aplimovil.ufriendly.data.entity.ClassSchedule
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject
import kotlinx.coroutines.flow.Flow
import edu.unicauca.aplimovil.ufriendly.data.relation.SubjectWithSchedules

class SubjectRepository(private val subjectDao: SubjectDao, private val classScheduleDao: ClassScheduleDao) {
    val allSubjects = subjectDao.getAllSubjects()
    val allSubjectsWithSchedules: Flow<List<SubjectWithSchedules>> = subjectDao.getSubjectsWithSchedules()

    suspend fun insert(subject: Subject) = subjectDao.insertSubject(subject)
    suspend fun insertSubjectWithSchedules(subject: Subject, schedules: List<ClassSchedule>) {
        // 1. Insertamos la materia y recuperamos su nuevo ID
        val newSubjectId = subjectDao.insertSubject(subject).toInt()

        // 2. Creamos copias de los horarios asignándoles el ID de la materia
        val schedulesWithId = schedules.map { it.copy(subjectId = newSubjectId) }

        // 3. Insertamos todos los horarios en la base de datos
        classScheduleDao.insertSchedules(schedulesWithId)
    }
    suspend fun update(subject: Subject) = subjectDao.updateSubject(subject)
    suspend fun delete(subject: Subject) = subjectDao.deleteSubject(subject)

    suspend fun getSubjectById(subjectId: Int) = subjectDao.getSubjectById(subjectId)
    suspend fun getSubjectWithTasks(subjectId: Int) = subjectDao.getSubjectWithTasks(subjectId)
    suspend fun getSubjectWithGrades(subjectId: Int) = subjectDao.getSubjectWithGrades(subjectId)
    suspend fun getSubjectWithSchedules(subjectId: Int) = subjectDao.getSubjectWithSchedules(subjectId)
    suspend fun getSubjectsWithSchedules() = subjectDao.getSubjectsWithSchedules()
}