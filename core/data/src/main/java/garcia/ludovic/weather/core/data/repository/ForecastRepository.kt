package garcia.ludovic.weather.core.data.repository

import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.data.model.Location

interface ForecastRepository {
    suspend fun getForecast(location: Location): Forecast
}
