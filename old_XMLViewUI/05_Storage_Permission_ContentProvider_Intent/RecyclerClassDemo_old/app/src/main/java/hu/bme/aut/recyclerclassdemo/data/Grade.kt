package hu.bme.aut.recyclerclassdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true) var gradeId: Long?,
    @ColumnInfo(name = "studentName") var studentName: String,
    @ColumnInfo(name = "subject") var subject: String,
    @ColumnInfo(name = "grade") var grade: Int,
)