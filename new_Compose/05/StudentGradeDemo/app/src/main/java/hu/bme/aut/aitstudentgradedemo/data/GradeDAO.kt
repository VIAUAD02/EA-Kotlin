package hu.bme.aut.aitstudentgradedemo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDAO { // Data Access Object - DAO
    @Query("SELECT * from grades")
    fun getAllGrades(): Flow<List<Grade>>

    @Query("SELECT * from grades ORDER BY studentname ASC")
    fun getAllGradesOrderByName(): Flow<List<Grade>>

    @Query("SELECT * from grades WHERE gradeId = :gradeId")
    fun getGrade(gradeId: Int): Flow<Grade>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(grade: Grade)

    @Update
    suspend fun update(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)

    @Query("DELETE from grades")
    suspend fun deleteAllGrades()
}