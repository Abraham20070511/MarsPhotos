/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia bajo la Apache License, Version 2.0
 * Puedes obtener una copia en: https://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.theme.MarsPhotosTheme

/**
 * Pantalla principal (HomeScreen) que cambia el contenido según el estado de la interfaz de usuario.
 *
 * @param marsUiState Estado actual (cargando, éxito, error).
 * @param modifier Modificador de estilo opcional.
 * @param contentPadding Relleno opcional para el contenido.
 */
@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    // Muestra una pantalla distinta según el estado
    when (marsUiState) {
        is MarsUiState.Loading ->
            LoadingScreen(modifier = modifier.fillMaxSize())
        is MarsUiState.Success ->
            ResultScreen(marsUiState.photos, modifier = modifier.fillMaxWidth())
        is MarsUiState.Error ->
            ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

/**
 * Pantalla mostrada cuando los datos se están cargando.
 * Muestra una imagen de carga.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp), // Tamaño de la imagen
        painter = painterResource(R.drawable.loading_img), // Imagen de recursos
        contentDescription = stringResource(R.string.loading) // Texto alternativo accesible
    )
}

/**
 * Pantalla mostrada cuando ocurre un error.
 * Muestra una imagen de error y un mensaje.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center, // Centrado vertical
        horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), // Imagen de error
            contentDescription = "" // Sin descripción (podría agregarse para accesibilidad)
        )
        Text(
            text = stringResource(R.string.loading_failed), // Texto de error
            modifier = Modifier.padding(16.dp)
        )
    }
}

/**
 * Pantalla mostrada cuando se cargan correctamente las fotos.
 * Por ahora muestra solo el número total como texto.
 *
 * @param photos Cadena con el número de fotos o descripción.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center, // Centra el contenido
        modifier = modifier
    ) {
        Text(text = photos) // Muestra el texto
    }
}

// Vista previa del estado de carga
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

// Vista previa del estado de error
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen()
    }
}

// Vista previa del estado de éxito con datos simulados
@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPhoto("$it", "") } // Lista ficticia de fotos
        ResultScreen(stringResource(R.string.placeholder_success)) // Texto simulado
    }
}
