package com.rodrigocarreon.spideytracker.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rodrigocarreon.spideytracker.ui.viewmodel.SightingViewModel
import com.rodrigocarreon.spideytracker.ui.viewmodel.ViewModelFactory

@Composable
fun MapScreen (
    modifier: Modifier = Modifier,
    viewModel: SightingViewModel = viewModel(factory = ViewModelFactory)
){
    val sightings by viewModel.sightings.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getSightings()
    }

    val newYork = LatLng(40.7128, -74.0060)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newYork, 11f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        sightings.forEach { sighting ->
            Marker(
                state = MarkerState(position = LatLng(sighting.latitude, sighting.longitude)),
                title = sighting.title,
                snippet = sighting.description
            )
        }
    }
}