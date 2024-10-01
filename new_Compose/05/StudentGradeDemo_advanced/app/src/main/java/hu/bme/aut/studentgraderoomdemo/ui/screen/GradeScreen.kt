package hu.bme.aut.studentgraderoomdemo.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import hu.bme.aut.studentgraderoomdemo.data.Course
import hu.bme.aut.studentgraderoomdemo.data.Grade
import androidx.compose.foundation.lazy.items

@Composable
fun GradeScreen(
    gradeViewModel: GradeViewModel = hiltViewModel()
) {
    val courses by gradeViewModel.getAllCourses()
        .collectAsState(emptyList())
    val gradesList by gradeViewModel.getAllGrades().collectAsState(emptyList())

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Add course")
        AddCourseForm(onCourseSaveClick = {
            courseName -> gradeViewModel.saveCourse(Course(courseName = courseName))
        })

        if (courses.isNotEmpty()){
            Divider(color = Color.Black, thickness = 1.dp,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
            Text(text = "Save grade")

            SaveGradeForm(courses = courses, onSaveGrade = {
                grade ->  gradeViewModel.saveGrade(grade)
            })

            Divider(
                color = Color.Black,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
            Text(text = "Grades")

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(gradesList) {
                    Text(text = "${it.courseName}, ${it.studentName}: ${it.grade}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun AddCourseForm(
    onCourseSaveClick: (String) -> Unit
) {
    var courseName by rememberSaveable { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(value = courseName,
            modifier = Modifier.weight(2f),
            label = { Text(text = "Course name") },
            onValueChange = {
                courseName = it
            }
        )
        Button(
            modifier = Modifier.weight(1f),
            onClick = {
                onCourseSaveClick(courseName)
            }
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SaveGradeForm(
    courses: List<Course>,
    onSaveGrade: (Grade) -> Unit
) {
    var selectedCourseId by remember { mutableStateOf(0) }
    var student by rememberSaveable { mutableStateOf("") }
    var gradeValue by rememberSaveable { mutableStateOf("") }

    CourseSelectorDropDown(courses = courses, onCourseSelected = {
        courseId -> selectedCourseId = courseId
    })

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = student,
                modifier = Modifier.weight(1f),
                label = { Text(text = "Student") },
                onValueChange = {
                    student = it
                }
            )
            OutlinedTextField(value = gradeValue,
                modifier = Modifier.weight(1f),
                label = { Text(text = "Grade") },
                onValueChange = {
                    gradeValue = it
                }
            )
        }
        Button(
            onClick = {
                onSaveGrade(
                    Grade(
                        0, courseId = selectedCourseId, student, gradeValue
                    )
                )
            }
        ) {
            Text(text = "Save grade")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CourseSelectorDropDown(courses: List<Course>, onCourseSelected: (Int) -> Unit) {
    var courseSelectorExpanded by remember { mutableStateOf(false) }
    var selectedCourseText by remember { mutableStateOf("") }

    var dropDownFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (courseSelectorExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedCourseText,
            onValueChange = { selectedCourseText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    dropDownFieldSize = coordinates.size.toSize()
                },
            label = { Text("Course") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { courseSelectorExpanded = !courseSelectorExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = courseSelectorExpanded,
            onDismissRequest = { courseSelectorExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownFieldSize.width.toDp() })
        ) {
            courses.forEach { course ->
                DropdownMenuItem(onClick = {
                    selectedCourseText = course.courseName
                    courseSelectorExpanded = false
                    onCourseSelected(course.courseId)
                }, text = { Text(text = course.courseName) })
            }
        }
    }
}