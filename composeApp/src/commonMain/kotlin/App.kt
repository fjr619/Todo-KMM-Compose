import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.navigation.AppGraph
import ui.screens.home.HomeScreen
import ui.screens.home.HomeViewModel
import ui.theme.AppTheme
import ui.util.withViewModelStoreOwner

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean = false,
) = withViewModelStoreOwner {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

        AppGraph()
    }
}