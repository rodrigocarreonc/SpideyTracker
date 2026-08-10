package com.rodrigocarreon.spideytracker.data.network

import com.rodrigocarreon.spideytracker.data.model.Sighting
import retrofit2.Response
import retrofit2.http.GET

interface SightingClient {
    @GET("/sightings.json")
    suspend fun getAllSightings(): Response<List<Sighting>>
}