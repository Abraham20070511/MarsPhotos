/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia Apache 2.0: https://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.marsphotos.network

// Importamos el modelo de datos que representa una foto de Marte
import com.example.marsphotos.model.MarsPhoto

// Importamos la anotación GET para definir la solicitud HTTP
import retrofit2.http.GET

/**
 * Interfaz pública que define el servicio de red para acceder a la API de Marte.
 * Esta interfaz es utilizada por Retrofit para generar automáticamente el código de red.
 */
interface MarsApiService {

    /**
     * Método suspendido que realiza una solicitud HTTP GET al endpoint "photos".
     * Devuelve una lista de objetos [MarsPhoto].
     * Al ser una función `suspend`, puede ser llamada desde una corrutina sin bloquear el hilo principal.
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
