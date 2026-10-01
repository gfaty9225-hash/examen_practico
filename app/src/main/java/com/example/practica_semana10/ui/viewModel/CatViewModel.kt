// ui/viewmodel/CatViewModel.kt
package com.example.practica_semana10.ui.viewModel.RetrofitClient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica_semana10.data.api.RetrofitClient
import com.example.practica_semana10.data.model.CatBreed
import com.example.practica_semana10.data.model.CatImage
import com.example.practica_semana10.ui.screen.UiState


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {

    // Estado para la colección de razas
    private val _breedsState = MutableStateFlow<UiState<List<CatBreed>>>(UiState.Loading)
    val breedsState: StateFlow<UiState<List<CatBreed>>> = _breedsState.asStateFlow()

    // Estado para el detalle de la raza seleccionada
    private val _detailState = MutableStateFlow<UiState<List<CatImage>>>(UiState.Loading)
    val detailState: StateFlow<UiState<List<CatImage>>> = _detailState.asStateFlow()

    init {
        fetchBreeds()
    }

    // Metodo para Endpoint 1 (Colección)
    fun fetchBreeds() {
        viewModelScope.launch {
            _breedsState.value = UiState.Loading
            try {
                val response = RetrofitClient.apiService.getBreeds()
                _breedsState.value = UiState.Success(response)
            } catch (e: Exception) {
                _breedsState.value = UiState.Error(e.localizedMessage ?: "Error al conectar con el servidor")
            }
        }
    }

    //Metodo para Endpoint 2 (Detalle)
    fun fetchImagesByBreed(breedId: String) {
        viewModelScope.launch {
            _detailState.value = UiState.Loading
            try {
                val response = RetrofitClient.apiService.getImagesByBreed(breedId)
                _detailState.value = UiState.Success(response)
            } catch (e: Exception) {
                _detailState.value = UiState.Error(e.localizedMessage ?: "Error al obtener el detalle")
            }
        }
    }
}