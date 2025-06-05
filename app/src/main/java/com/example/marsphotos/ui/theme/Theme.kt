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

package com.example.marsphotos.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Paleta de colores para el modo oscuro
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Paleta de colores para el modo claro
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

/**
 * Función composable que aplica el tema de la aplicación.
 *
 * @param darkTheme indica si se debe usar el tema oscuro (por defecto se usa el modo del sistema).
 * @param dynamicColor desactiva el uso de colores dinámicos (opcional, false por defecto).
 * @param content contenido composable que se mostrará dentro del tema.
 */
@Composable
fun MarsPhotosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Usa el tema oscuro si el sistema lo indica
    dynamicColor: Boolean = false, // Colores dinámicos desactivados por fines educativos
    content: @Composable () -> Unit
) {
    // Determina el esquema de color que se usará
    val colorScheme = when {
        // Si se permite y el dispositivo es Android 12 o superior, usa colores dinámicos
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        // Si no, usa el tema oscuro o claro definido manualmente
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Obtiene la vista actual del sistema
    val view = LocalView.current

    // Si no estamos en modo de edición (como en Android Studio Preview)
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Cambia el color de la barra de estado al color primario del tema actual
            window.statusBarColor = colorScheme.primary.toArgb()
            // Configura el color del texto de la barra de estado (oscuro o claro)
            WindowCompat
                .getInsetsController(window, view)
                .isAppearanceLightStatusBars = darkTheme
        }
    }

    // Aplica el tema de Material 3 con los colores, tipografía y formas personalizadas
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content // Contenido visual que se renderizará dentro del tema
    )
}
