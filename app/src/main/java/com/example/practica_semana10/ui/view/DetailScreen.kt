package com.example.practica_semana10.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.practica_semana10.ui.screen.UiState
import com.example.practica_semana10.ui.viewModel.RetrofitClient.CatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    breedId: String,
    breedName: String,
    viewModel: CatViewModel
) {
    val state by viewModel.detailState.collectAsStateWithLifecycle()

    LaunchedEffect(breedId) {
        viewModel.fetchImagesByBreed(breedId)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Detalle: $breedName") }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val result = state) {
                is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is UiState.Error -> ErrorView(message = result.message, onRetry = { viewModel.fetchImagesByBreed(breedId) })
                is UiState.Success -> {
                    val images = result.data
                    if (images.isEmpty()) {
                        Text(
                            text = "No se encontraron detalles para esta raza",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            items(images) { image ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(text = "ID Imagen: ${image.id}", style = MaterialTheme.typography.titleSmall)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(text = "URL: ${image.url}", style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}