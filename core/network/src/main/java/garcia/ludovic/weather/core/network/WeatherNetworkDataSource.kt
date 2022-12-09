package garcia.ludovic.weather.core.network

import garcia.ludovic.weather.core.network.model.NetworkForecast

interface WeatherNetworkDataSource {
    suspend fun getForecast(latitude: Float, longitude: Float): NetworkForecast
}
