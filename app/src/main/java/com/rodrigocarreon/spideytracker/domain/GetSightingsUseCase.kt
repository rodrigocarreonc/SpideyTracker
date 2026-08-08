package com.rodrigocarreon.spideytracker.domain

import com.rodrigocarreon.spideytracker.data.SightingRepository
import com.rodrigocarreon.spideytracker.data.model.Sighting

class GetSightingsUseCase (
    private val repository: SightingRepository
){
    suspend operator fun invoke() : List<Sighting> = repository.getAllSightings()
}