/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia Apache 2.0: https://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.marsphotos.ui.screens

// Importaciones necesarias para ViewModel, estados y corrutinas
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.model.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Representa el estado de la interfaz de usuario de la pantalla principal (Home).
 * Puede ser uno de los siguientes:
 */
sealed interface MarsUiState {
    // Estado de éxito: contiene un String con el número de fotos
    data class Success(val photos: String) : MarsUiState

    // Estado de error
    object Error : MarsUiState

    // Estado de carga
    object Loading : MarsUiState
}

/**
 * ViewModel de la pantalla principal.
 * Se comunica con el repositorio para obtener datos de la API de Marte.
 */
class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository) : ViewModel() {

    // Variable de estado observable que representa el estado actual de la UI
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set // Solo el ViewModel puede modificarla

    /**
     * Se llama automáticamente al inicializar el ViewModel.
     * Inicia la solicitud de fotos de Marte.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Función que lanza una corrutina para obtener las fotos de Marte desde la API.
     * Actualiza el estado según el resultado.
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            // Cambia el estado a "cargando"
            marsUiState = MarsUiState.Loading

            // Intenta obtener los datos desde el repositorio
            marsUiState = try {
                val listResult = marsPhotosRepository.getMarsPhotos()
                // Si es exitoso, cambia el estado a "éxito" con el número de fotos
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (e: IOException) {
                // Error de red u otra entrada/salida
                MarsUiState.Error
            } catch (e: HttpException) {
                // Error HTTP (por ejemplo, código 404 o 500)
                MarsUiState.Error
            }
        }
    }

    /**
     * Fábrica para crear instancias del MarsViewModel.
     * Permite inyectar dependencias como el repositorio desde la aplicación.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Obtiene la instancia de la aplicación
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                // Accede al repositorio desde el contenedor de dependencias
                val marsPhotosRepository = application.container.marsPhotosRepository
                // Crea el ViewModel pasando el repositorio
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
