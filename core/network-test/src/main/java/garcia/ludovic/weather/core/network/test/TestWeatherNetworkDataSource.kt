package garcia.ludovic.weather.core.network.test

import garcia.ludovic.weather.core.network.WeatherNetworkDataSource
import garcia.ludovic.weather.core.network.model.NetworkForecast
import garcia.ludovic.weather.core.network.model.NetworkIcon
import garcia.ludovic.weather.core.network.model.NetworkMain
import garcia.ludovic.weather.core.network.model.NetworkRain
import garcia.ludovic.weather.core.network.model.NetworkWeather
import garcia.ludovic.weather.core.network.model.NetworkWind
import javax.inject.Singleton

@Singleton
class TestWeatherNetworkDataSource : WeatherNetworkDataSource {

    override suspend fun getForecast(latitude: Float, longitude: Float): NetworkForecast =
        NetworkForecast(
            listOf(NetworkWeather("Clouds", NetworkIcon.BROKEN_CLOUDS)),
            NetworkMain(4.01f, 2.76f, 93f),
            NetworkWind(1.54f),
            NetworkRain(1.2f),
            "Reims"
        )
}
