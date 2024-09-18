package hu.bme.tododemo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.tododemo.data.TodoItem
import hu.bme.tododemo.repository.RoomTodoRepository
import hu.bme.tododemo.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SORTBY(public val sortId: Int) {
    TITLE(0), DESCRIPTION(1), CREATEDATE(2);

    companion object {
        fun fromInt(value: Int): SORTBY {
            return when (value) {
                0 -> { TITLE }
                1 -> { DESCRIPTION }
                else -> { CREATEDATE }
            }
        }
    }
}


@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val todoRepository: RoomTodoRepository
) : ViewModel() {

    fun getAllToDoList(sort: SORTBY): Flow<List<TodoItem>> {
        return todoRepository.getAllTodosStream().map {
            when (sort) {
                SORTBY.TITLE -> {
                    it.sortedBy { it.title }
                }
                SORTBY.DESCRIPTION -> {
                    it.sortedBy { it.description }
                }
                else -> {
                    it.sortedBy { it.createDate }
                }
            }
        }
    }

    fun addTodoList(todoItem: TodoItem) {
        viewModelScope.launch {
            todoRepository.insertTodo(todoItem)
        }
    }

    fun removeTodoItem(todoItem: TodoItem) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todoItem)
        }
    }

    fun editTodoItem(originalTodo: TodoItem, editedTodo: TodoItem) {
        viewModelScope.launch {
            todoRepository.updateTodo(editedTodo)
        }
    }

    fun changeTodoState(todoItem: TodoItem, value: Boolean) {
        val newTodoItem = todoItem.copy()
        newTodoItem.isDone = value
        viewModelScope.launch {
            todoRepository.updateTodo(newTodoItem)
        }
    }

    fun clearAllTodos() {
        viewModelScope.launch {
            todoRepository.deleteAllTodos()
        }
    }

    suspend fun storeSortOrder(
        sortOrder: SORTBY
    ) {
        settingsRepository.setSortOrder(sortOrder.sortId)
    }

    fun getSortOrder(): Flow<SORTBY> {
        return settingsRepository.getSortOrder().map {
            SORTBY.fromInt(it)
        }
    }

    suspend fun storeWasStarted(
        wasStarted: Boolean
    ) {
        settingsRepository.setWasStarted(wasStarted)
    }

    fun getWasStarted(): Flow<Boolean> {
        return settingsRepository.getWasStarted()
    }
}
