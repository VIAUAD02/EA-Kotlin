package hu.bme.aut.studentgraderoomdemo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDAO {
    @Query(
        "SELECT courseName, studentName, grade FROM grades, courses " +
        "WHERE grades.courseId=courses.courseId"
    )
    fun getAllGradesWithCourse(): Flow<List<GradeDTO>>

    @Query("SELECT * from grades")
    fun getAllGrades(): Flow<List<Grade>>

    @Query("SELECT * from grades WHERE gradeId = :gradeId")
    fun getGradeById(gradeId: Int): Flow<Grade>

    @Query("SELECT * from grades ORDER BY studentName ASC")
    fun getAllGradesOrderByName(): Flow<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(grade: Grade)

    @Update
    suspend fun updateGrade(grade: Grade)

    @Delete
    suspend fun deleteGrade(grade: Grade)

    @Query("DELETE from grades")
    suspend fun deleteAllGrades()

    @Query("SELECT * from courses")
    fun getAllCourses(): Flow<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(course: Course)
}