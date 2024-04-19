package di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import domain.TodoTask
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ui.screens.SharedViewModel
import ui.screens.home.HomeViewModel
import ui.screens.task.TaskViewModel
import kotlin.reflect.KClass

internal object ViewModelFac : KoinComponent {
    @Composable
    fun getHomeViewModel(): HomeViewModel {
        return viewModel(
            HomeViewModel::class,
            factory = viewModelFactory {
                initializer { HomeViewModel(get()) }
            })
    }

    @Composable
    fun getTaskViewModel(
        currentTask: TodoTask,
    ): TaskViewModel {
        return viewModel(
            modelClass = TaskViewModel::class,
            factory = viewModelFactory {
                initializer { TaskViewModel(currentTask, get()) }
            })
    }

    @Composable
    fun NavBackStackEntry.sharedViewModel(
        navController: NavHostController,
    ): SharedViewModel {
        val factory = viewModelFactory {
            initializer { SharedViewModel() }
        }

        val navGraphRoute = destination.parent?.route ?: return viewModel(
            modelClass = SharedViewModel::class,
            factory = factory
        )

        val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }

        return viewModel(
            modelClass = SharedViewModel::class,
            viewModelStoreOwner = parentEntry,
            factory = factory
        )
    }
}