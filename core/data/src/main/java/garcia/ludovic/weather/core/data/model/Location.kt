package garcia.ludovic.weather.core.data.model

import android.location.Location as AndroidLocation

data class Location(val latitude: Float, val longitude: Float)

fun AndroidLocation.asLocal(): Location =
    Location(latitude.toFloat(), longitude.toFloat())
