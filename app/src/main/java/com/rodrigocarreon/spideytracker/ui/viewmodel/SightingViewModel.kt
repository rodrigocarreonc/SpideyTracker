package com.rodrigocarreon.spideytracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigocarreon.spideytracker.data.model.Sighting
import com.rodrigocarreon.spideytracker.domain.GetSightingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SightingViewModel(
    private val getSightingsUseCase: GetSightingsUseCase
) : ViewModel() {
    private val _sightings = MutableStateFlow<List<Sighting>>(emptyList())
    val sightings: StateFlow<List<Sighting>> = _sightings.asStateFlow()

    fun getSightings() {
        viewModelScope.launch {
            val result = getSightingsUseCase()
            _sightings.value = result
        }
    }
}