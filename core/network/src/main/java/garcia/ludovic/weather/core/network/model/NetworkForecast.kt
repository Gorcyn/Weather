package garcia.ludovic.weather.core.network.model

import garcia.ludovic.weather.core.network.model.serializer.NetworkIconSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class NetworkIcon(val code: String) {
    CLEAR_SKY("01"),
    FEW_CLOUDS("02"),
    SCATTERED_CLOUDS("03"),
    BROKEN_CLOUDS("04"),
    SHOWER_RAIN("09"),
    RAIN("10"),
    THUNDERSTORM("11"),
    SNOW("13"),
    MIST("50")
}

@Serializable
data class NetworkWeather(
    val main: String,
    @Serializable(with = NetworkIconSerializer::class)
    val icon: NetworkIcon
)

@Serializable
data class NetworkMain(
    val temp: Float,
    val feels_like: Float,
    val humidity: Float
)

@Serializable
data class NetworkWind(val speed: Float)

@Serializable
data class NetworkRain(@SerialName("1h") val volume_1h: Float)

@Serializable
data class NetworkForecast(
    val weather: List<NetworkWeather>,
    val main: NetworkMain,
    val wind: NetworkWind,
    val rain: NetworkRain? = null,
    val name: String
)
