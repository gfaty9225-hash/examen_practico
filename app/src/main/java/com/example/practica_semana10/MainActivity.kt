package com.example.practica_semana10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practica_semana10.ui.theme.Practicasemana10Theme
import com.example.practica_semana10.ui.view.BreedsScreen
import com.example.practica_semana10.ui.view.DetailScreen
import com.example.practica_semana10.ui.viewModel.RetrofitClient.CatViewModel

class MainActivity : ComponentActivity() {

    // Instancia del ViewModel asociada al ciclo de vida de la Activity
    private val viewModel: CatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practicasemana10Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: CatViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "breeds"
    ) {
        // Pantalla 1: Colección/Lista de Razas
        composable("breeds") {
            BreedsScreen(
                viewModel = viewModel,
                onBreedClick = { breedId, breedName ->
                    navController.navigate("detail/$breedId/$breedName")
                }
            )
        }

        // Pantalla 2: Detalle de la Raza seleccionada
        composable(
            route = "detail/{breedId}/{breedName}",
            arguments = listOf(
                navArgument("breedId") { type = NavType.StringType },
                navArgument("breedName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val breedId = backStackEntry.arguments?.getString("breedId") ?: ""
            val breedName = backStackEntry.arguments?.getString("breedName") ?: ""

            DetailScreen(
                breedId = breedId,
                breedName = breedName,
                viewModel = viewModel
            )
        }
    }
}