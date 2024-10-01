package hu.bme.aut.studentgraderoomdemo.repository

import hu.bme.aut.studentgraderoomdemo.data.Course
import hu.bme.aut.studentgraderoomdemo.data.Grade
import hu.bme.aut.studentgraderoomdemo.data.GradeDAO
import hu.bme.aut.studentgraderoomdemo.data.GradeDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GradeDbRepository @Inject constructor(private val gradeDAO: GradeDAO){
    fun getAllGradesWithCoursesStream(): Flow<List<GradeDTO>> {
        return gradeDAO.getAllGradesWithCourse()
    }

    suspend fun insertGrade(grade: Grade) {
        gradeDAO.insertGrade(grade)
    }

    fun getAllCoursesStream(): Flow<List<Course>> {
        return gradeDAO.getAllCourses()
    }

    suspend fun insertCourse(course: Course) {
        gradeDAO.insertCourse(course)
    }
}