package hu.bme.aut.recyclerclassdemo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GradeDao {

    @Query("SELECT * FROM grades")
    fun getAllGradeItems(): List<Grade>

    @Insert
    fun insertGradeItem(grade: Grade) : Long

    @Delete
    fun deleteGradeItem(grade: Grade)

    @Update
    fun updateGradeItem(grade: Grade)

}
