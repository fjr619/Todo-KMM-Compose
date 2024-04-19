package domain

import org.mongodb.kbson.ObjectId

data class TodoTask (
    val id: ObjectId = ObjectId(),
    val title: String = "",
    val description: String = "",
    val favorite: Boolean = false,
    val completed: Boolean = false
)