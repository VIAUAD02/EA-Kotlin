package hu.bme.aut.recyclerclassdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.recyclerclassdemo.adapter.GradesAdapter
import hu.bme.aut.recyclerclassdemo.data.Grade
import hu.bme.aut.recyclerclassdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var gradesList = mutableListOf<Grade>(
            Grade("Peter", "Android", 2),
            Grade("Hassan", "iOS", 5)
        )

        var adapter = GradesAdapter(this, gradesList)
        binding.recyclerGrades.adapter = adapter

        binding.fabAddItem.setOnClickListener {
            adapter.addItem(
                Grade("Hassan", "iOS", 5)
            )
        }

    }
}