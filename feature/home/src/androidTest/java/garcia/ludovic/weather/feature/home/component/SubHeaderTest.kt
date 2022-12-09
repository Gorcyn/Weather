package garcia.ludovic.weather.feature.home.component

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import garcia.ludovic.weather.core.design.test.assertBackgroundColor
import garcia.ludovic.weather.core.design.test.assertTextColor
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.R
import org.junit.Rule
import org.junit.Test

class SubHeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var colorScheme: ColorScheme

    @Test
    fun subHeader_displays_title() {
        composeTestRule.setContent {
            WeatherTheme {
                colorScheme = WeatherTheme.colorScheme

                SubHeader(
                    title = R.string.weather_now_title
                )
            }
        }
        composeTestRule.onNodeWithText("Weather Now")
            .assertExists()
            .assertTextColor(colorScheme.onBackground)

        composeTestRule.onNodeWithTag("SUB_HEADER_COLUMN")
            .assertBackgroundColor(colorScheme.background)
    }
}
