/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia bajo la Apache License, Version 2.0 (la "Licencia");
 * no puedes usar este archivo excepto en cumplimiento con la Licencia.
 * Puedes obtener una copia de la Licencia en:
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * A menos que sea requerido por la ley aplicable o acordado por escrito,
 * el software distribuido bajo la Licencia se distribuye "TAL CUAL",
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ya sean expresas o implícitas.
 * Consulta la Licencia para el lenguaje específico que rige los permisos y limitaciones.
 */

package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.marsphotos.ui.MarsPhotosApp
import com.example.marsphotos.ui.theme.MarsPhotosTheme

// Clase principal de la app. Hereda de ComponentActivity, que es una actividad base
// para aplicaciones que usan Jetpack Compose.
class MainActivity : ComponentActivity() {
    // Método que se ejecuta cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Habilita el diseño que se extiende hasta los bordes de la pantalla
        enableEdgeToEdge()

        // Llama al onCreate de la clase base
        super.onCreate(savedInstanceState)

        // Establece el contenido de la actividad usando Jetpack Compose
        setContent {
            // Aplica el tema personalizado definido en MarsPhotosTheme
            MarsPhotosTheme {
                // Surface es un contenedor que proporciona color de fondo, elevación, etc.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ocupa todo el tamaño de la pantalla
                ) {
                    // Llama a la función composable principal de la app
                    MarsPhotosApp()
                }
            }
        }
    }
}
