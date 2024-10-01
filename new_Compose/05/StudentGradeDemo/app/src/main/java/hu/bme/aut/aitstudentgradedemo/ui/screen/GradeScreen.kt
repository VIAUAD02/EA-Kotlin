package hu.bme.aut.aitstudentgradedemo.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import hu.bme.aut.aitstudentgradedemo.data.Grade

import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GradeScreen(
    gradeViewModel: GradeViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    var studentName by rememberSaveable { mutableStateOf("") }
    var grade by rememberSaveable { mutableStateOf("") }

    val gradeList by gradeViewModel.getAllGrades().collectAsState(emptyList())

    Column {
        OutlinedTextField(
            label = { Text(text = "Student name") },
            value = studentName,
            onValueChange = {
                studentName = it
            })
        OutlinedTextField(
            label = { Text(text = "Grade") },
            value = grade,
            onValueChange = {
                grade = it
            })

        Button(onClick = {
            gradeViewModel.deleteAllGrades()
        }) {
            Text(text = "Delete all")
        }

        Button(onClick = {

            coroutineScope.launch(Dispatchers.IO) {
                gradeViewModel.addGrade(
                    Grade(
                        studentName = studentName,
                        grade = grade
                    )
                )
            }

        }) {
            Text(text = "Save")
        }

        LazyColumn {
            items(gradeList) {
                Text(
                    text = "${it.studentName} - ${it.grade}",
                    fontSize = 22.sp
                )
            }
        }
    }

}