package garcia.ludovic.weather.feature.home.resources

import androidx.annotation.RawRes
import garcia.ludovic.weather.core.data.model.Forecast.Icon
import garcia.ludovic.weather.feature.home.R

val Icon.lottieAnimation: Int
    @RawRes
    get() = when (this) {
        Icon.CLEAR_SKY -> R.raw.weather_4804_sunny
        Icon.FEW_CLOUDS -> R.raw.weather_4800_partly_cloudy
        Icon.SCATTERED_CLOUDS -> R.raw.weather_4806_windy
        Icon.BROKEN_CLOUDS -> R.raw.weather_4791_foggy
        Icon.SHOWER_RAIN -> R.raw.weather_4801_partly_shower
        Icon.RAIN -> R.raw.weather_4801_partly_shower
        Icon.THUNDERSTORM -> R.raw.weather_4805_thunder
        Icon.SNOW -> R.raw.weather_4793_snow
        Icon.MIST -> R.raw.weather_4795_mist
    }
