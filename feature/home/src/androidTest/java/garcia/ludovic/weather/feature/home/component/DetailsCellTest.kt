package garcia.ludovic.weather.feature.home.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.font.FontWeight
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Humidity
import garcia.ludovic.weather.core.design.test.assertFontWeight
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.R
import org.junit.Rule
import org.junit.Test

class DetailsCellTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val humidity = Humidity(43f, "%")

    @Test
    fun detailsCell_displays_title_value_and_unit() {
        composeTestRule.setContent {
            WeatherTheme {
                DetailsCell(
                    R.string.weather_now_humidity,
                    R.drawable.ic_outline_water_drop_24,
                    humidity
                )
            }
        }
        composeTestRule.onNodeWithText("Humidity")
            .assertExists()

        composeTestRule.onNodeWithText("43%")
            .assertExists()
            .assertFontWeight(FontWeight.Bold)
    }
}
