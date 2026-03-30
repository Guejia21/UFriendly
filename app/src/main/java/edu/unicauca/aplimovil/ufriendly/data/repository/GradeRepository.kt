package edu.unicauca.aplimovil.ufriendly.data.repository

import edu.unicauca.aplimovil.ufriendly.data.dao.GradeDao
import edu.unicauca.aplimovil.ufriendly.data.entity.Grade
import edu.unicauca.aplimovil.ufriendly.data.relation.GradeWithSubject
import kotlinx.coroutines.flow.Flow

class GradeRepository(private val gradeDao: GradeDao) {
    val allGrades = gradeDao.getAllGrades()
    val allGradesWithSubject: Flow<List<GradeWithSubject>> = gradeDao.getGradesWithSubject()
    suspend fun insert(grade: Grade) = gradeDao.insert(grade)
    suspend fun update(grade: Grade) = gradeDao.update(grade)
    suspend fun delete(grade: Grade) = gradeDao.delete(grade)
    suspend fun getGradesBySubject(subjectId: Int) = gradeDao.getGradesBySubject(subjectId)
}