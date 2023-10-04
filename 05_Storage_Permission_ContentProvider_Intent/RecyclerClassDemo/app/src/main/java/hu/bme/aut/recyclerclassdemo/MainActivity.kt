package hu.bme.aut.recyclerclassdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.recyclerclassdemo.adapter.GradesAdapter
import hu.bme.aut.recyclerclassdemo.data.AppDatabase
import hu.bme.aut.recyclerclassdemo.data.Grade
import hu.bme.aut.recyclerclassdemo.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: GradesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.fabAddItem.setOnClickListener {
            thread {
                val newGrade = Grade(null, "Joe", "Android", 5)
                AppDatabase.getInstance(this).gradDao().insertGradeItem(
                    newGrade
                )
                runOnUiThread {
                    adapter.addItem(newGrade)
                }
            }
        }

    }

    private fun initRecyclerView() {
        thread {
            val grades = AppDatabase.getInstance(this).gradDao().getAllGradeItems()
            runOnUiThread {
                adapter = GradesAdapter(this, grades)
                binding.recyclerGrades.adapter = adapter
            }
        }
    }
}