package ma.fst.projet.navigationdrawerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import ma.fst.projet.navigationdrawerdemo.ui.theme.NavigationDrawerDemoTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDrawerDemoTheme {
                NavigationDrawerDemoApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun NavigationDrawerDemoApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            // Display different screens based on the current destination
            when (currentDestination) {
                AppDestinations.HOME -> HomeScreen(Modifier.padding(innerPadding))
                AppDestinations.FAVORITES -> FavoritesScreen(Modifier.padding(innerPadding))
                AppDestinations.PROFILE -> ProfileScreen(Modifier.padding(innerPadding))
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Home", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox),
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Welcome to the Home Screen!")
    }
}

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Welcome to the Favorites Screen!")
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Welcome to the Profile Screen!")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationDrawerDemoTheme {
        HomeScreen(Modifier)
    }
}