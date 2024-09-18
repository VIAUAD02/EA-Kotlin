package hu.ait.todolayoutinflaterdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hu.ait.todolayoutinflaterdemo.databinding.ActivityMainBinding
import hu.ait.todolayoutinflaterdemo.databinding.TodoRowBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAdd.setOnClickListener {
            addTodo()
        }

    }

    private fun addTodo() {
        val todoRowBinding = TodoRowBinding.inflate(layoutInflater)

        todoRowBinding.tvTodoText.text = binding.etTodo.text.toString()
        todoRowBinding.btnDelete.setOnClickListener {
            binding.todoContainer.removeView(todoRowBinding.root)
        }

        binding.todoContainer.addView(todoRowBinding.root)
    }


}