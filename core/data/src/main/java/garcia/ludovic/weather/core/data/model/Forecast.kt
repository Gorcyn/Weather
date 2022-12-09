package garcia.ludovic.weather.core.data.model

import androidx.annotation.StringRes
import garcia.ludovic.weather.core.data.R
import garcia.ludovic.weather.core.network.model.NetworkForecast

data class Forecast(
    val locality: String,
    val weather: String,
    val icon: Icon,
    val temperature: ValueWithUnit.Temperature,
    val apparentTemperature: ValueWithUnit.ApparentTemperature,
    val humidity: ValueWithUnit.Humidity,
    val windSpeed: ValueWithUnit.WindSpeed,
    val precipitation: ValueWithUnit.Precipitation?
) {
    enum class Icon {
        CLEAR_SKY,
        FEW_CLOUDS,
        SCATTERED_CLOUDS,
        BROKEN_CLOUDS,
        SHOWER_RAIN,
        RAIN,
        THUNDERSTORM,
        SNOW,
        MIST
    }

    sealed class ValueWithUnit(open val value: Float, open val unit: String, @StringRes val format: Int) {
        data class Temperature(override val value: Float, override val unit: String) : ValueWithUnit(value, unit, R.string.weather_now_temperature_format)
        data class ApparentTemperature(override val value: Float, override val unit: String) : ValueWithUnit(value, unit, R.string.weather_now_apparent_format)
        data class Humidity(override val value: Float, override val unit: String) : ValueWithUnit(value, unit, R.string.weather_now_humidity_format)
        data class WindSpeed(override val value: Float, override val unit: String) : ValueWithUnit(value, unit, R.string.weather_now_wind_format)
        data class Precipitation(override val value: Float, override val unit: String) : ValueWithUnit(value, unit, R.string.weather_now_precipitation_format)
    }
}

fun NetworkForecast.asLocal(): Forecast =
    Forecast(
        locality = name,
        weather = weather.first().main,
        icon = Forecast.Icon.values()[weather.first().icon.ordinal],
        temperature = Forecast.ValueWithUnit.Temperature(main.temp, "°C"),
        apparentTemperature = Forecast.ValueWithUnit.ApparentTemperature(main.feels_like, "°C"),
        humidity = Forecast.ValueWithUnit.Humidity(main.humidity, "%"),
        windSpeed = Forecast.ValueWithUnit.WindSpeed(wind.speed, "m/s"),
        precipitation = rain?.volume_1h?.run { Forecast.ValueWithUnit.Precipitation(this, "mm") }
    )
