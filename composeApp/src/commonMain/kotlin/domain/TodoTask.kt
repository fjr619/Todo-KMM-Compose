package domain

import org.mongodb.kbson.ObjectId

data class TodoTask (
    val id: ObjectId,
    val title: String,
    val description: String,
    val favorite: Boolean,
    val completed: Boolean,
)