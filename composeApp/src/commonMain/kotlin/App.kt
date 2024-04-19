import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.screens.home.HomeScreen
import ui.screens.home.HomeViewModel
import ui.theme.AppTheme
import ui.util.ViewModelFac

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean = false,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        //https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "HOME"
        ) {
            composable(
                route = "HOME"
            ) {
                val homeViewModel = ViewModelFac.getHomeViewModel(HomeViewModel::class)
                val state by homeViewModel.state.collectAsState()

                HomeScreen(
                    state = state,
                    navigateToTask = {

                    }
                )
            }
        }

//        Surface(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            var showContent by remember { mutableStateOf(false) }
//            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Button(onClick = { showContent = !showContent }) {
//                    Text("Click me!")
//                }
//                AnimatedVisibility(showContent) {
//                    val greeting = remember { Greeting().greet() }
//                    Column(
//                        Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Image(painterResource(Res.drawable.compose_multiplatform), null)
//                        Text("Compose: $greeting")
//                    }
//                }
//            }
//        }
    }
}