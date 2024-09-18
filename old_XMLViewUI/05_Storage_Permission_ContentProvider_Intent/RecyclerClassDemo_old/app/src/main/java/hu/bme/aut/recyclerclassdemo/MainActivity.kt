package hu.bme.aut.recyclerclassdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.recyclerclassdemo.adapter.GradesAdapter
import hu.bme.aut.recyclerclassdemo.data.AppDatabase
import hu.bme.aut.recyclerclassdemo.data.Grade
import hu.bme.aut.recyclerclassdemo.databinding.ActivityMainBinding
import java.util.UUID

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: GradesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.fabAddItem.setOnClickListener {
            Thread {
                var newItem = Grade(null,"Hassan", "iOS", 5)

                var newId = AppDatabase.getInstance(this@MainActivity).gradDao()
                    .insertGradeItem(newItem)
                newItem.gradeId = newId
                runOnUiThread {
                    adapter.addItem(newItem)
                }
            }.start()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        Thread {
            var gradeItemList =
                AppDatabase.getInstance(this@MainActivity).
                gradDao().getAllGradeItems()

            runOnUiThread {
                adapter = GradesAdapter(this, gradeItemList)
                binding.recyclerGrades.adapter = adapter
            }
        }.start()
    }
}