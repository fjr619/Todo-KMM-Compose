package data.local

import domain.TodoTask
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ToDoTask: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var title: String = ""
    var description: String = ""
    var favorite: Boolean = false
    var completed: Boolean = false
}

fun ToDoTask.toDomain() = TodoTask(
    id = this._id,
    title = this.title,
    description = this.description,
    favorite = this.favorite,
    completed = this.completed
)

fun TodoTask.toData(): ToDoTask {
    val task = ToDoTask()
    task.title = this.title
    task.description = this.description
    task.favorite = this.favorite
    task.completed = this.completed
    return task
}