package di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import ui.screens.SharedViewModel
import ui.screens.home.HomeViewModel
import ui.screens.task.TaskViewModel

actual val viewModelModule: Module = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> TaskViewModel(params.get(), get()) }
    viewModel { SharedViewModel() }
}