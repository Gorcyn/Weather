package garcia.ludovic.weather.core.data.model

import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.ApparentTemperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Humidity
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Precipitation
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Temperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.WindSpeed
import garcia.ludovic.weather.core.network.model.NetworkForecast
import garcia.ludovic.weather.core.network.model.NetworkIcon
import garcia.ludovic.weather.core.network.model.NetworkMain
import garcia.ludovic.weather.core.network.model.NetworkRain
import garcia.ludovic.weather.core.network.model.NetworkWeather
import garcia.ludovic.weather.core.network.model.NetworkWind
import org.junit.Assert.assertEquals
import org.junit.Test

class ForecastTest {

    @Test
    fun `NetworkForecast is mapped to local Forecast`() {
        val networkForecast = NetworkForecast(
            listOf(NetworkWeather("Clouds", NetworkIcon.BROKEN_CLOUDS)),
            NetworkMain(4.01f, 2.76f, 93f),
            NetworkWind(1.54f),
            NetworkRain(1.2f),
            "Reims"
        )

        val expected = Forecast(
            locality = "Reims",
            weather = "Clouds",
            icon = Forecast.Icon.BROKEN_CLOUDS,
            temperature = Temperature(4.01f, "°C"),
            apparentTemperature = ApparentTemperature(2.76f, "°C"),
            humidity = Humidity(93f, "%"),
            windSpeed = WindSpeed(1.54f, "m/s"),
            precipitation = Precipitation(1.2f, "mm")
        )
        assertEquals(expected, networkForecast.asLocal())
    }
}
