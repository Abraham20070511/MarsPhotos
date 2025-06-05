/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia bajo Apache 2.0: https://www.apache.org/licenses/LICENSE-2.0
 */

@file:OptIn(ExperimentalMaterial3Api::class) // Permite usar APIs marcadas como experimentales de Material 3

package com.example.marsphotos.ui

// Importaciones necesarias para Composable, Material 3 y gestión de estado
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.R
import com.example.marsphotos.ui.screens.HomeScreen
import com.example.marsphotos.ui.screens.MarsViewModel

/**
 * Composable principal de la aplicación. Define la estructura de la app usando Scaffold.
 */
@Composable
fun MarsPhotosApp() {
    // Define el comportamiento de desplazamiento del TopAppBar (permite que desaparezca al hacer scroll hacia abajo)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    // Scaffold proporciona una estructura básica de pantalla (barra superior, contenido, etc.)
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), // Conecta el scroll con la barra
        topBar = { MarsTopAppBar(scrollBehavior = scrollBehavior) } // Define la barra superior
    ) {
        // Surface es un contenedor que aplica el color de fondo del tema actual
        Surface(
            modifier = Modifier.fillMaxSize() // Ocupa todo el tamaño disponible
        ) {
            // Se obtiene una instancia del ViewModel usando su Factory personalizada
            val marsViewModel: MarsViewModel =
                viewModel(factory = MarsViewModel.Factory)

            // Se muestra la pantalla principal con el estado actual y el relleno del Scaffold
            HomeScreen(
                marsUiState = marsViewModel.marsUiState,
                contentPadding = it // `it` contiene el relleno generado por el Scaffold
            )
        }
    }
}

/**
 * Composable que define la barra superior de la app con alineación centrada.
 */
@Composable
fun MarsTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior, // Aplica el comportamiento de scroll
        title = {
            Text(
                text = stringResource(R.string.app_name), // Título de la app desde los recursos
                style = MaterialTheme.typography.headlineSmall, // Aplica estilo de título
            )
        },
        modifier = modifier
    )
}
