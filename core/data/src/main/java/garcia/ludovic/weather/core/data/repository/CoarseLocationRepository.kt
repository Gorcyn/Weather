package garcia.ludovic.weather.core.data.repository

import garcia.ludovic.weather.core.data.model.Location

interface CoarseLocationRepository {
    suspend fun getCurrentLocation(): Location?
}
