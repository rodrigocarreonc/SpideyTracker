package com.rodrigocarreon.spideytracker.data.model

import com.google.gson.annotations.SerializedName

enum class SightingType {
    @SerializedName("rumor") RUMOR,
    @SerializedName("confirmed") CONFIRMED,
    @SerializedName("event") EVENT,
    @SerializedName("uknown") UNKWOWN,
}