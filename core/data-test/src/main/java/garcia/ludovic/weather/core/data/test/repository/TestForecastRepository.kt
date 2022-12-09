package garcia.ludovic.weather.core.data.test.repository

import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.ApparentTemperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Humidity
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Precipitation
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Temperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.WindSpeed
import garcia.ludovic.weather.core.data.model.Location
import garcia.ludovic.weather.core.data.repository.ForecastRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class TestForecastRepository @Inject constructor() : ForecastRepository {

    var fail: Boolean = false

    override suspend fun getForecast(location: Location): Forecast = delay(250L).run {
        if (fail) {
            throw Exception("Network not available.")
        } else {
            Forecast(
                locality = "Reims",
                weather = "Clouds",
                icon = Forecast.Icon.BROKEN_CLOUDS,
                temperature = Temperature(4.01f, "°C"),
                apparentTemperature = ApparentTemperature(2.76f, "°C"),
                humidity = Humidity(93f, "%"),
                windSpeed = WindSpeed(1.54f, "m/s"),
                precipitation = Precipitation(1.2f, "mm")
            )
        }
    }
}
