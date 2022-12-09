package garcia.ludovic.weather.feature.home.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import garcia.ludovic.weather.core.design.test.assertBackgroundColor
import garcia.ludovic.weather.core.design.test.assertTextColor
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import org.junit.Rule
import org.junit.Test

class HeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun header_displays_locality() {
        composeTestRule.setContent {
            WeatherTheme {
                Header(
                    locality = "Reims",
                    colors = HeaderColors(Color.Red, Color.Blue)
                )
            }
        }
        composeTestRule.onNodeWithText("Reims")
            .assertExists()
            .assertTextColor(Color.Blue)

        composeTestRule.onNodeWithTag("HEADER_COLUMN")
            .assertBackgroundColor(Color.Red)
    }
}
