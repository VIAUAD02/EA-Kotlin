package hu.bme.aut.recyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.recyclerviewdemo.R
import hu.bme.aut.recyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*

class TodoAdapter(val context: Context) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    val todoItems = mutableListOf<Todo>(
        Todo("Todo1", "2020. okt", false),
        Todo("Todo2", "2020. sept", false)    ,
        Todo("Todo3", "2020. nov", false)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoItems[position]

        holder.cbTodo.text = todo.todoText
        holder.cbTodo.isChecked = todo.isDone
        holder.tvDate.text = todo.createDate
    }

    override fun getItemCount() =  todoItems.size

    fun addTodo(todo: Todo) {
        todoItems.add(todo)
        notifyItemInserted(todoItems.lastIndex)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbTodo = itemView.cbTodo
        val tvDate = itemView.tvDate
    }
}