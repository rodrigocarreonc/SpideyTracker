package com.rodrigocarreon.spideytracker.data

import com.rodrigocarreon.spideytracker.data.model.Sighting
import com.rodrigocarreon.spideytracker.data.network.SightingService

class SightingRepository(
    private val service: SightingService
) {
    suspend fun getAllSightings(): List<Sighting>{
        return service.getSightings()
    }
}