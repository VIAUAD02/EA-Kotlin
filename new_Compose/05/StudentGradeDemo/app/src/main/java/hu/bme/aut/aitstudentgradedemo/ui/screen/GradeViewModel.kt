package hu.bme.aut.aitstudentgradedemo.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.aitstudentgradedemo.data.AppDatabase
import hu.bme.aut.aitstudentgradedemo.data.Grade
import hu.bme.aut.aitstudentgradedemo.data.GradeDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GradeViewModel @Inject constructor(
    private val gradeDAO: GradeDAO
) : ViewModel() {
    fun getAllGrades(): Flow<List<Grade>> {
        return gradeDAO.getAllGrades()
    }

    fun deleteAllGrades() {
        viewModelScope.launch(Dispatchers.IO) {
            gradeDAO.deleteAllGrades()
        }
    }

    suspend fun addGrade(grade: Grade) {
        gradeDAO.insert(grade)
    }
}