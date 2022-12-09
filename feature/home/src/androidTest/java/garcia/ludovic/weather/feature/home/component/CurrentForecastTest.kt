package garcia.ludovic.weather.feature.home.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.design.test.assertTextColor
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import org.junit.Rule
import org.junit.Test

class CurrentForecastTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun currentForecastTest_displays_temperature_and_weather() {
        composeTestRule.setContent {
            WeatherTheme {
                CurrentForecast(
                    forecast = Forecast(
                        locality = "Reims",
                        weather = "Clouds",
                        icon = Forecast.Icon.BROKEN_CLOUDS,
                        temperature = Forecast.ValueWithUnit.Temperature(4.01f, "°C"),
                        apparentTemperature = Forecast.ValueWithUnit.ApparentTemperature(
                            2.76f,
                            "°C"
                        ),
                        humidity = Forecast.ValueWithUnit.Humidity(93f, "%"),
                        windSpeed = Forecast.ValueWithUnit.WindSpeed(1.54f, "m/s"),
                        precipitation = Forecast.ValueWithUnit.Precipitation(1.2f, "mm")
                    ),
                    Color.Red
                )
            }
        }
        composeTestRule.onNodeWithText("4°C")
            .assertExists()
            .assertTextColor(Color.Red)

        composeTestRule.onNodeWithText("Clouds")
            .assertExists()
            .assertTextColor(Color.Red)
    }
}
