package com.example.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab06.ui.theme.Lab06Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab06Theme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { UserProfileScreen(navController) }
        composable("inicio") { HomeScreen(navController) }
        composable("config") { SettingsScreen(navController) }
        composable("info") { InfoScreen(navController) }
        composable("notificaciones") { NotificationsScreen(navController) }
    }
}

@Composable
fun CustomScaffold(
    navController: NavHostController,
    showFab: Boolean = false,
    onFabClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { CustomTopBar() },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            if (showFab && onFabClick != null) {
                CustomFAB(onClick = onFabClick)
            }
        },
        content = content
    )
}

@Composable
fun HomeScreen(navController: NavHostController) {
    var clickCount by remember { mutableStateOf(0) }

    CustomScaffold(
        navController = navController,
        showFab = true,
        onFabClick = { clickCount++ }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Bienvenido a la pantalla de inicio.")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Has presionado el botón $clickCount veces.")
            Spacer(modifier = Modifier.height(16.dp))
            CustomContent(padding = PaddingValues(0.dp))
        }
    }
}


@Composable
fun UserProfileScreen(navController: NavHostController) {
    CustomScaffold(navController) { padding ->
        Text("Bienvenido al perfil de usuario.", modifier = Modifier.padding(padding).padding(16.dp))
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    CustomScaffold(navController) { padding ->
        Text("Pantalla de configuración.", modifier = Modifier.padding(padding).padding(16.dp))
    }
}

@Composable
fun InfoScreen(navController: NavHostController) {
    CustomScaffold(navController) { padding ->
        Text("Pantalla de información.", modifier = Modifier.padding(padding).padding(16.dp))
    }
}

@Composable
fun NotificationsScreen(navController: NavHostController) {
    CustomScaffold(navController) { padding ->
        Text("Pantalla de notificaciones.", modifier = Modifier.padding(padding).padding(16.dp))
    }
}

@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Text(
            fontSize = 24.sp,
            text = "+"
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(text = "My app content")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text("Sample Title") },
        navigationIcon = {
            IconButton(onClick = { /* acción de menú si la deseas */ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menú")
            }
        },
        actions = {
            // Sin botones en la parte superior derecha
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("inicio") }) {
                Icon(Icons.Filled.Home, contentDescription = "Inicio")
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(Icons.Filled.Person, contentDescription = "Perfil")
            }
            IconButton(onClick = { navController.navigate("config") }) {
                Icon(Icons.Filled.Settings, contentDescription = "Configuración")
            }
            IconButton(onClick = { navController.navigate("info") }) {
                Icon(Icons.Filled.Info, contentDescription = "Información")
            }
            IconButton(onClick = { navController.navigate("notificaciones") }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notificaciones")
            }
        }
    }
}


