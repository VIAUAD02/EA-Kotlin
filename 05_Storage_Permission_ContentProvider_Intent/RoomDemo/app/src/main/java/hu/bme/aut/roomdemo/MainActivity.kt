package hu.bme.aut.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import hu.bme.aut.roomdemo.data.AppDatabase
import hu.bme.aut.roomdemo.data.Grade
import hu.bme.aut.roomdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val grade = Grade(null, binding.etStudentId.text.toString(),
                binding.etGrade.text.toString())

            val dbThread = Thread {
                AppDatabase.getInstance(this@MainActivity).gradeDao().
                insertGrades(grade)
            }
            dbThread.start()
        }

        binding.btnSearch.setOnClickListener {
            /*val dbThread = Thread {
                val grades = AppDatabase.getInstance(this@MainActivity)
                    .gradeDao().getAllGrades()
                runOnUiThread {
                    binding.tvResult.text = ""
                    grades.forEach {
                        binding.tvResult.append("${it.studentId} ${it.grade}\n")
                    }
                }
            }
            dbThread.start()*/


            lifecycleScope.launch(Dispatchers.Main) {
                val list: List<Grade> =
                    lifecycleScope.async(Dispatchers.IO) {
                        queryGrades()
                    }.await()

                binding.tvResult.text = ""
                list.forEach {
                    binding.tvResult.append("Name: ${it.studentId} grade: ${it.grade}\n")
                }
            }
        }
    }

    suspend fun queryGrades(): List<Grade> {
        //Thread.sleep(5000)
        return AppDatabase.getInstance(this@MainActivity).gradeDao().getAllGrades()
    }
}
