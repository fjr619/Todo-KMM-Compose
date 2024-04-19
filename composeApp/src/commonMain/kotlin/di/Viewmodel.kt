package di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.mongodb.kbson.ObjectId
import ui.screens.home.HomeViewModel
import ui.screens.task.TaskViewModel
import kotlin.reflect.KClass

internal object ViewModelFac : KoinComponent {
    @Composable
    fun getHomeViewModel(
        modelClass: KClass<HomeViewModel>
    ): HomeViewModel {
        return viewModel(
            modelClass,
            factory = viewModelFactory {
                initializer { HomeViewModel(get()) }
            })
    }

    @Composable
    fun getTaskViewModel(
        currentTaskId: ObjectId? = null,
        modelClass: KClass<TaskViewModel>
    ): TaskViewModel {
        return viewModel(
            modelClass,
            factory = viewModelFactory {
                initializer { TaskViewModel(currentTaskId, get()) }
            })
    }
}