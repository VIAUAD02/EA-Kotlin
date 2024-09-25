package hu.ait.tododemo.data

import hu.ait.tododemo.R

data class TodoItem(
    val id: String,
    val title:String,
    val description:String,
    val createDate:String,
    var priority:TodoPriority,
    var isDone: Boolean
)

enum class TodoPriority {
    NORMAL, HIGH;

    fun getIcon(): Int {
        // The this is the value of this enum object
        return if (this == NORMAL) R.drawable.normal
        else R.drawable.important
    }

}