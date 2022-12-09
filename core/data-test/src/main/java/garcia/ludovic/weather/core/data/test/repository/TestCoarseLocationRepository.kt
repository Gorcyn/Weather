package garcia.ludovic.weather.core.data.test.repository

import garcia.ludovic.weather.core.data.model.Location
import garcia.ludovic.weather.core.data.repository.CoarseLocationRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class TestCoarseLocationRepository @Inject constructor() : CoarseLocationRepository {

    var fail: Boolean = false

    override suspend fun getCurrentLocation(): Location? = delay(250L).run {
        if (fail) {
            null
        } else {
            Location(latitude = 49.258f, longitude = 4.031f)
        }
    }
}
