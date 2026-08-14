package com.rodrigocarreon.spideytracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rodrigocarreon.spideytracker.data.model.Sighting

val RetroBeige = Color(0xFFF2EEDD)
val PixelBorder = Color(0xFF1E1E1E)

val API_URLBASE = "https://spideytracker.vercel.app"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SightingBottomSheet(
    sighting: Sighting,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = Color.Transparent,
        dragHandle = null
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(RetroBeige)
                    .border(4.dp, PixelBorder)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(sighting.image != null){
                    AsyncImage(
                        model = API_URLBASE + sighting.image,
                        contentDescription = "Sighting Photo",
                        modifier = Modifier
                            .size(100.dp)
                            .border(4.dp, PixelBorder),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = sighting.title,
                        fontWeight = FontWeight.Bold,
                        color = PixelBorder,
                        fontFamily = FontFamily.Monospace
                    )

                    Text(
                        text = sighting.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = PixelBorder,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}