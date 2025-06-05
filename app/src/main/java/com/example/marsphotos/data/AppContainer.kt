/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licencia Apache 2.0: https://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.marsphotos.data

// Importamos el servicio de red definido anteriormente
import com.example.marsphotos.network.MarsApiService

// Importamos Retrofit para hacer solicitudes HTTP
import retrofit2.Retrofit

// Importamos el convertidor para JSON usando kotlinx.serialization
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

// Librería para manipular JSON
import kotlinx.serialization.json.Json

// Para especificar el tipo MIME del contenido (application/json)
import okhttp3.MediaType.Companion.toMediaType

/**
 * Contenedor de inyección de dependencias (DI) a nivel de aplicación.
 * Define la interfaz que expone el repositorio de fotos de Marte.
 */
interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

/**
 * Implementación concreta del contenedor de dependencias.
 * Se usa para centralizar la creación de objetos compartidos en toda la app.
 *
 * Las variables se inicializan de forma perezosa (`lazy`), es decir, sólo cuando se necesitan.
 */
class DefaultAppContainer : AppContainer {

    // URL base de la API de Marte
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Se crea una instancia de Retrofit usando un convertidor de JSON compatible con kotlinx.serialization.
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Instancia del servicio Retrofit que implementa MarsApiService.
     * Se crea de forma perezosa para que sólo se construya si es necesario.
     */
    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * Se crea e inyecta una implementación concreta del repositorio de fotos de Marte
     * que usará el servicio Retrofit para acceder a la API.
     */
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}
