package hu.bme.aut.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.recyclerviewdemo.adapter.TodoAdapter
import hu.bme.aut.recyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(this)
        rwTodo.adapter = todoAdapter

        btnAdd.setOnClickListener {
            todoAdapter.addTodo(
                Todo(
                    etTodo.text.toString(),
                    Date(System.currentTimeMillis()).toString(),
                    false
                )
            )
        }
    }
}