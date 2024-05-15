package di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import domain.TodoTask
import org.koin.compose.currentKoinScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import ui.screens.SharedViewModel
import ui.screens.home.HomeViewModel
import ui.screens.task.TaskViewModel
import kotlin.reflect.KClass

internal object ViewModelFac : KoinComponent {
//    @Composable
//    fun getHomeViewModel(): HomeViewModel {
//        val scope = currentKoinScope()
//        return viewModel { HomeViewModel(get()) }
//    }

//    @Composable
//    fun getTaskViewModel(
//        currentTask: TodoTask,
//    ): TaskViewModel {
//        return viewModel { TaskViewModel(currentTask, get()) }
//    }

//    @Composable
//    fun NavBackStackEntry.sharedViewModel(
//        navController: NavHostController,
//    ): SharedViewModel {
//        val navGraphRoute = destination.parent?.route ?: return viewModel {
//            SharedViewModel()
//        }
//
//        val parentEntry = remember(this) {
//            navController.getBackStackEntry(navGraphRoute)
//        }
//
//        return viewModel(
//            viewModelStoreOwner = parentEntry
//        ) {
//            SharedViewModel()
//        }
//    }
}

