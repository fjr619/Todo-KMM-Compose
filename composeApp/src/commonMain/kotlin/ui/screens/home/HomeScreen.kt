package ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.util.ViewModelFac


@Composable
fun HomeScreen() {
    val homeViewModel = ViewModelFac.getHomeViewModel(HomeViewModel::class)
    val state by homeViewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("ini home screen")
    }
}