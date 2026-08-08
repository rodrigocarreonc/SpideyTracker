package com.rodrigocarreon.spideytracker.data.model

import com.google.gson.annotations.SerializedName
import com.rodrigocarreon.spideytracker.data.model.SightingType

data class Sighting(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: SightingType = SightingType.UNKWOWN,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
)