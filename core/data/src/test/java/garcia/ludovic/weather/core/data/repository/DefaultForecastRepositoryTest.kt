package garcia.ludovic.weather.core.data.repository

import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.data.model.Location
import garcia.ludovic.weather.core.network.test.TestWeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultForecastRepositoryTest {

    private val defaultForecastRepository = DefaultForecastRepository(
        TestWeatherNetworkDataSource(),
        Dispatchers.IO
    )

    @Test
    fun `getForecast gets a forecast`() = runTest {
        val location = Location(12f, 24f)
        val forecast = defaultForecastRepository.getForecast(location)

        val expected = Forecast(
            locality = "Reims",
            weather = "Clouds",
            icon = Forecast.Icon.BROKEN_CLOUDS,
            temperature = Forecast.ValueWithUnit.Temperature(4.01f, "°C"),
            apparentTemperature = Forecast.ValueWithUnit.ApparentTemperature(2.76f, "°C"),
            humidity = Forecast.ValueWithUnit.Humidity(93f, "%"),
            windSpeed = Forecast.ValueWithUnit.WindSpeed(1.54f, "m/s"),
            precipitation = Forecast.ValueWithUnit.Precipitation(1.2f, "mm")
        )

        assertEquals(expected, forecast)
    }
}
