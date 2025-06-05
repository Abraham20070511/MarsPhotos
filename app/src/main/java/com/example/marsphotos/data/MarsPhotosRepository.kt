/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia bajo Apache 2.0: https://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.marsphotos.data

// Importa el modelo de datos que representa una foto de Marte
import com.example.marsphotos.model.MarsPhoto

// Importa el servicio de red que se comunica con la API de Marte
import com.example.marsphotos.network.MarsApiService

/**
 * Interfaz del repositorio que define las operaciones que se pueden hacer para obtener fotos de Marte.
 *
 * El repositorio actúa como una capa intermedia entre la fuente de datos (API) y el ViewModel o UI.
 */
interface MarsPhotosRepository {
    /**
     * Función suspendida que obtiene una lista de objetos [MarsPhoto] desde la API de Marte.
     */
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

/**
 * Implementación concreta de [MarsPhotosRepository] que usa la red (Retrofit) para obtener los datos.
 *
 * Esta clase se conecta al servicio [MarsApiService] para hacer llamadas HTTP reales.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService // Inyecta el servicio de red
) : MarsPhotosRepository {

    /**
     * Llama al método `getPhotos()` del servicio de red para obtener las fotos.
     * Esta llamada se hace de forma suspendida (asincrónica) usando corrutinas.
     */
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}
