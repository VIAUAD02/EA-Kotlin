package hu.bme.aut.aitstudentgradedemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "grades")
data class Grade(
    @PrimaryKey(autoGenerate = true)  val gradeId: Int = 0,
    @ColumnInfo(name = "studentName")  var studentName: String,
    @ColumnInfo(name = "grade") var grade: String
)
