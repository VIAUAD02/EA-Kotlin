package hu.bme.aut.roomdemo.data

import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "grade")
data class Grade(@PrimaryKey(autoGenerate = true) var gradeId: Long?,
                 @ColumnInfo(name = "studentid") var studentId: String,
                 @ColumnInfo(name = "grade") var grade: String,
)