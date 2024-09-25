package hu.ait.tododemo.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.tododemo.data.TodoItem
import hu.ait.tododemo.data.TodoPriority
import java.util.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import hu.ait.tododemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = viewModel(),
    navController: NavController
) {
    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
    ) {
        TopAppBar(
            title = {
                Text("Todo App")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor =
                MaterialTheme.colorScheme.secondaryContainer
            ),
            actions = {
                IconButton(onClick = {
                    navController.navigate(
                        "summary/${todoListViewModel.getAllTodoNum()}/${todoListViewModel.getImportantTodoNum()}"
                    )
                }) {
                    Icon(Icons.Filled.Info, null)
                }
                IconButton(onClick = {showAddDialog = true}) {
                    Icon(Icons.Filled.Add, null )
                }

                IconButton(onClick = {
                    todoListViewModel.clearAllTodos()
                }) {
                    Icon(Icons.Filled.Delete, null )
                }
            })

        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            if (showAddDialog) {
                AddNewTodoForm(
                    onDialogClose = {showAddDialog = false}
                )
            }

            LazyColumn() {
                items(todoListViewModel.getAllToDoList()) {
                    TodoCard(todoItem = it,
                        onTodoCheckChange = { checked ->
                            todoListViewModel.changeTodoState(it, checked)
                        },
                        onRemoveItem = {
                            todoListViewModel.removeTodoItem(it)
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewTodoForm(
    todoListViewModel: TodoListViewModel = viewModel(),
    onDialogClose: ()->Unit = {}
) {
    var newTodoTitle by remember { mutableStateOf("") }
    var newTodoDesc by remember { mutableStateOf("") }
    var newTodoPriority by remember { mutableStateOf(false) }

    Dialog (onDismissRequest = onDialogClose) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 6.dp)
        ) {

            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(value = newTodoTitle,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Todo title") },
                        onValueChange = {
                            newTodoTitle = it
                        }
                    )
                    OutlinedTextField(value = newTodoDesc,
                        modifier = Modifier.weight(1f),
                        label = { Text(text = "Description") },
                        onValueChange = {
                            newTodoDesc = it
                        }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = newTodoPriority, onCheckedChange = {
                        newTodoPriority = it
                    })
                    Text(text = "Important")
                }

                Button(onClick = {
                    todoListViewModel.addTodoList(
                        TodoItem(
                            UUID.randomUUID().toString(),
                            newTodoTitle,
                            newTodoDesc,
                            Date(System.currentTimeMillis()).toString(),
                            if (newTodoPriority) TodoPriority.HIGH else TodoPriority.NORMAL,
                            false
                        )
                    )

                    onDialogClose()
                }) {
                    Text(text = "Add")
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoCard(
    todoItem: TodoItem,
    onTodoCheckChange: (Boolean) -> Unit = {},
    onRemoveItem: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp)
    ) {
        var expanded by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(10.dp)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = todoItem.priority.getIcon()),
                    contentDescription = "Priority",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = todoItem.title,
                        textDecoration =
                        if (todoItem.isDone) {
                            TextDecoration.LineThrough
                        } else {
                            TextDecoration.None
                        }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = todoItem.isDone,
                        onCheckedChange = {
                            onTodoCheckChange(it)
                        },
                    )
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.clickable {
                            onRemoveItem()
                        },
                        tint = Color.Red
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded)
                                Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = if (expanded) {
                                "Less"
                            } else {
                                "More"
                            }
                        )
                    }

                }
            }

            if (expanded) {

                Text(text = todoItem.description)
                Text(
                    text = todoItem.createDate,
                    style = TextStyle(fontSize = 12.sp)
                )

            }
        }
    }
}