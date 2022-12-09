package garcia.ludovic.weather.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.data.model.Forecast.Icon
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.ApparentTemperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Humidity
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Precipitation
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Temperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.WindSpeed
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.resources.lottieAnimation

@Composable
fun CurrentForecast(
    forecast: Forecast,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(forecast.icon.lottieAnimation)
    )

    Column(
        modifier = modifier
    ) {
        val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = stringResource(id = forecast.temperature.format, forecast.temperature.value, forecast.temperature.unit),
            style = WeatherTheme.typography.displayLarge,
            color = contentColor
        )
        Text(
            text = forecast.weather,
            style = WeatherTheme.typography.bodyMedium,
            color = contentColor
        )
    }
}

@Composable
@Preview
fun CurrentForecast_Preview() {
    WeatherTheme {
        CurrentForecast(
            Forecast(
                "Reims",
                "Clouds",
                Icon.BROKEN_CLOUDS,
                Temperature(4.01f, "°C"),
                ApparentTemperature(2.76f, "°C"),
                Humidity(93f, "%"),
                WindSpeed(1.54f, "m/s"),
                Precipitation(3.16f, "mm")
            ),
            Color.White
        )
    }
}
