package hu.bme.aut.studentgraderoomdemo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.studentgraderoomdemo.data.Course
import hu.bme.aut.studentgraderoomdemo.data.Grade
import hu.bme.aut.studentgraderoomdemo.data.GradeDTO
import hu.bme.aut.studentgraderoomdemo.repository.GradeDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GradeViewModel @Inject constructor(
    private val gradeDbRepository: GradeDbRepository) : ViewModel() {

    fun getAllCourses(): Flow<List<Course>> {
        return gradeDbRepository.getAllCoursesStream()
    }

    fun saveCourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            gradeDbRepository.insertCourse(course)
        }
    }

    fun getAllGrades(): Flow<List<GradeDTO>> {
        return gradeDbRepository.getAllGradesWithCoursesStream()
    }

    fun saveGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            gradeDbRepository.insertGrade(grade)
        }
    }
}