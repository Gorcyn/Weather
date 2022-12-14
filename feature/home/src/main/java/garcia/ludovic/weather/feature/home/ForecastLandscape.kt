package garcia.ludovic.weather.feature.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.component.CurrentForecast
import garcia.ludovic.weather.feature.home.component.DetailsCell
import garcia.ludovic.weather.feature.home.component.Header
import garcia.ludovic.weather.feature.home.component.HeaderColors
import garcia.ludovic.weather.feature.home.component.SubHeader

@Composable
fun ForecastLandscape(
    forecast: Forecast,
    containerColor: Color,
    contentColor: Color,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(containerColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(4f)
            ) {
                Header(
                    forecast.locality,
                    HeaderColors(containerColor, contentColor)
                )
                CurrentForecast(
                    forecast,
                    contentColor = contentColor,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f)
                        .background(containerColor)
                        .padding(16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .background(WeatherTheme.colorScheme.background)
                    .weight(3f)
            ) {
                SubHeader(R.string.weather_now_title)
                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        DetailsCell(
                            title = R.string.weather_now_apparent,
                            icon = R.drawable.ic_outline_thermostat_24,
                            valueWithUnit = forecast.apparentTemperature
                        )
                        DetailsCell(
                            title = R.string.weather_now_precipitation,
                            icon = R.drawable.ic_outline_umbrella_24,
                            valueWithUnit = forecast.precipitation
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        DetailsCell(
                            title = R.string.weather_now_wind,
                            icon = R.drawable.ic_outline_wind_power_24,
                            valueWithUnit = forecast.windSpeed
                        )
                        DetailsCell(
                            title = R.string.weather_now_humidity,
                            icon = R.drawable.ic_outline_water_drop_24,
                            valueWithUnit = forecast.humidity
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(widthDp = 900, heightDp = 450)
fun ForecastLandscape_Preview() {
    WeatherTheme {
        ForecastLandscape(
            Forecast(
                "Reims",
                "Clouds",
                Forecast.Icon.BROKEN_CLOUDS,
                Forecast.ValueWithUnit.Temperature(4.01f, "??C"),
                Forecast.ValueWithUnit.ApparentTemperature(2.76f, "??C"),
                Forecast.ValueWithUnit.Humidity(93f, "%"),
                Forecast.ValueWithUnit.WindSpeed(1.54f, "m/s"),
                Forecast.ValueWithUnit.Precipitation(3.16f, "mm")
            ),
            Color.Blue,
            Color.White
        )
    }
}
