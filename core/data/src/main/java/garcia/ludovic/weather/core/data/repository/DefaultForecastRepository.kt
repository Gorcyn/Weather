package garcia.ludovic.weather.core.data.repository

import garcia.ludovic.weather.core.common.Dispatcher
import garcia.ludovic.weather.core.common.WeatherDispatchers
import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.data.model.Location
import garcia.ludovic.weather.core.data.model.asLocal
import garcia.ludovic.weather.core.network.WeatherNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultForecastRepository @Inject constructor(
    private val network: WeatherNetworkDataSource,
    @Dispatcher(WeatherDispatchers.IO) private val defaultDispatcher: CoroutineDispatcher
) : ForecastRepository {

    override suspend fun getForecast(location: Location): Forecast = withContext(defaultDispatcher) {
        network.getForecast(location.latitude, location.longitude).asLocal()
    }
}
