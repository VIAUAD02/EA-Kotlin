package hu.bme.recyclerviewdemosubjects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import hu.bme.recyclerviewdemosubjects.adapter.SubjectsRecyclerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recSubj = findViewById<RecyclerView>(R.id.recyclerSubjects)
        recSubj.adapter = SubjectsRecyclerAdapter(this)

    }
}