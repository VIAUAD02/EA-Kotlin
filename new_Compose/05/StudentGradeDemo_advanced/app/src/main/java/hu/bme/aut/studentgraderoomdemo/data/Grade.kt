package hu.bme.aut.studentgraderoomdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) val gradeId: Int = 0,
    @ColumnInfo(name = "courseId") var courseId: Int,
    @ColumnInfo(name = "studentName") var studentName: String,
    @ColumnInfo(name = "grade") var grade: String,
)

data class GradeDTO(
    var courseName: String,
    var studentName: String,
    var grade: String,
)