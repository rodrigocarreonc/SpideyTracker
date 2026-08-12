package com.rodrigocarreon.spideytracker.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rodrigocarreon.spideytracker.R
import com.rodrigocarreon.spideytracker.core.bitmapDescriptorFromVector
import com.rodrigocarreon.spideytracker.data.model.SightingType
import com.rodrigocarreon.spideytracker.ui.viewmodel.SightingViewModel
import com.rodrigocarreon.spideytracker.ui.viewmodel.ViewModelFactory

@Composable
fun MapScreen (
    modifier: Modifier = Modifier,
    viewModel: SightingViewModel = viewModel(factory = ViewModelFactory)
){
    val context = LocalContext.current

    val icon_rumor = R.drawable.red_pin
    val icon_confirmed = R.drawable.green_pin
    val icon_event = R.drawable.white_pin

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
            val iconResId = when(sighting.type){
                SightingType.RUMOR -> icon_rumor
                SightingType.CONFIRMED -> icon_confirmed
                SightingType.EVENT -> icon_event
                SightingType.UNKWOWN -> icon_event
            }

            val mapIcon = bitmapDescriptorFromVector(context, iconResId)

            Marker(
                state = MarkerState(position = LatLng(sighting.latitude, sighting.longitude)),
                title = sighting.title,
                snippet = sighting.description,
                icon = mapIcon
            )
        }
    }
}