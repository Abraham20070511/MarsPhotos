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

import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer

// Clase de aplicación personalizada que se extiende de Application.
// Esta clase se ejecuta antes que cualquier otra clase cuando se lanza la app.
class MarsPhotosApplication : Application() {

    // Declaración de una propiedad lateinit del tipo AppContainer.
    // Esta propiedad se usará para acceder a las dependencias compartidas de la app.
    lateinit var container: AppContainer

    // Método que se llama cuando se crea la aplicación.
    override fun onCreate() {
        super.onCreate()

        // Se inicializa el contenedor de dependencias con una implementación por defecto.
        container = DefaultAppContainer()
    }
}
