package com.rodrigocarreon.spideytracker.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rodrigocarreon.spideytracker.R
import com.rodrigocarreon.spideytracker.core.bitmapDescriptorFromVector
import com.rodrigocarreon.spideytracker.data.model.Sighting
import com.rodrigocarreon.spideytracker.data.model.SightingType
import com.rodrigocarreon.spideytracker.ui.components.SightingBottomSheet
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

    val spideyBanner = R.drawable.banner
    val topFrame = R.drawable.topframe
    val bottomFrame = R.drawable.bottomframe
    val rightFrame = R.drawable.rightframe
    val leftFrame = R.drawable.leftframe

    var selectedSighting by remember { mutableStateOf<Sighting?>(null) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val sightings by viewModel.sightings.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getSightings()
    }

    val newYork = LatLng(40.7128, -74.0060)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newYork, 11f)
    }

    Box(modifier = modifier.fillMaxSize()){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                compassEnabled = false,
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                zoomControlsEnabled = false
            )
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
                    icon = mapIcon,
                    onClick = {
                        selectedSighting = sighting
                        showBottomSheet = true
                        true
                    }
                )
            }
        }

        Image(
            painter = painterResource(id = topFrame),
            null,
            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth().size(30.dp),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = bottomFrame),
            null,
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().size(30.dp),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = leftFrame),
            null,
            modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight().size(30.dp),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = rightFrame),
            null,
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().size(30.dp),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = spideyBanner),
            contentDescription= "Logo SpideyTracker",
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 12.dp).fillMaxWidth(0.75f).size(40.dp)
        )
    }

    if(showBottomSheet && selectedSighting != null){
        SightingBottomSheet(
            sighting = selectedSighting!!,
            onDismiss = {
                showBottomSheet = false
                selectedSighting = null
            }
        )
    }
}