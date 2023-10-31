package hu.bme.tododemo.repository

import hu.bme.tododemo.data.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodosStream(): Flow<List<TodoItem>>

    fun getTodoStream(id: Int): Flow<TodoItem?>

    suspend fun insertTodo(todo: TodoItem)

    suspend fun deleteTodo(todo: TodoItem)

    suspend fun deleteAllTodos()

    suspend fun updateTodo(todo: TodoItem)
}