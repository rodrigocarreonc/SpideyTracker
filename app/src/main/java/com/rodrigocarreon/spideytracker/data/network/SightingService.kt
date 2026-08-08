package com.rodrigocarreon.spideytracker.data.network

import com.rodrigocarreon.spideytracker.data.model.Sighting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class SightingService (
    private val api: SightingClient
){
    suspend fun getSightings(): List<Sighting>{
        return withContext(Dispatchers.IO){
            val response = api.getAllSightings()
            response.body() ?: emptyList()
        }
    }
}