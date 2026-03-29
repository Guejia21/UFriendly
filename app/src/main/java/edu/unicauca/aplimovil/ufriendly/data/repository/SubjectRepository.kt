package edu.unicauca.aplimovil.ufriendly.data.repository

import edu.unicauca.aplimovil.ufriendly.data.dao.SubjectDao
import edu.unicauca.aplimovil.ufriendly.data.entity.Subject

class SubjectRepository(private val subjectDao: SubjectDao) {
    val allSubjects = subjectDao.getAllSubjects()
    suspend fun insert(subject: Subject) = subjectDao.insertSubject(subject)
    suspend fun update(subject: Subject) = subjectDao.updateSubject(subject)
    suspend fun delete(subject: Subject) = subjectDao.deleteSubject(subject)

    suspend fun getSubjectById(subjectId: Int) = subjectDao.getSubjectById(subjectId)
    suspend fun getSubjectWithTasks(subjectId: Int) = subjectDao.getSubjectWithTasks(subjectId)
    suspend fun getSubjectWithGrades(subjectId: Int) = subjectDao.getSubjectWithGrades(subjectId)
    suspend fun getSubjectWithSchedules(subjectId: Int) = subjectDao.getSubjectWithSchedules(subjectId)
    suspend fun getSubjectsWithSchedules() = subjectDao.getSubjectsWithSchedules()


}


