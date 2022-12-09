package garcia.ludovic.weather.feature.home

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import garcia.ludovic.weather.core.common.HiltComponentActivity
import garcia.ludovic.weather.core.design.test.assertBackgroundColor
import garcia.ludovic.weather.core.design.test.assertFontWeight
import garcia.ludovic.weather.core.design.test.assertTextColor
import garcia.ludovic.weather.core.design.theme.LocalSnackbarHostState
import garcia.ludovic.weather.core.design.theme.LocalSystemUiController
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeRouteTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    private lateinit var colorScheme: ColorScheme

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun homeRoute_shows_forecast() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            WeatherTheme {
                colorScheme = WeatherTheme.colorScheme

                val systemUiController = rememberSystemUiController()
                val snackbarHostState = remember { SnackbarHostState() }
                CompositionLocalProvider(
                    LocalSnackbarHostState provides snackbarHostState,
                    LocalSystemUiController provides systemUiController
                ) {
                    HomeRoute {}
                }
            }
        }
        restorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithText("Reims")
                .fetchSemanticsNodes().size == 1
        }

        val temperatureContainerColor = Color(0, 4 * 25, 255)
        val temperatureContentColor = Color.White

        // Header
        composeTestRule.onNodeWithText("Reims")
            .assertExists()
            .assertTextColor(temperatureContentColor)

        // Forecast
        composeTestRule.onNodeWithTag("HEADER_COLUMN")
            .assertBackgroundColor(temperatureContainerColor)

        composeTestRule.onNodeWithText("4°C")
            .assertExists()
            .assertTextColor(temperatureContentColor)

        composeTestRule.onNodeWithText("Clouds")
            .assertExists()
            .assertTextColor(temperatureContentColor)

        // Weather Now
        composeTestRule.onNodeWithText("Weather Now")
            .assertExists()
            .assertTextColor(colorScheme.onBackground)

        composeTestRule.onNodeWithTag("SUB_HEADER_COLUMN")
            .assertBackgroundColor(colorScheme.background)

        // Apparent
        composeTestRule.onNodeWithText("Apparent")
            .assertExists()

        composeTestRule.onNodeWithText("3°C")
            .assertExists()
            .assertFontWeight(FontWeight.Bold)

        // Wind
        composeTestRule.onNodeWithText("Wind")
            .assertExists()

        composeTestRule.onNodeWithText("2 m/s")
            .assertExists()
            .assertFontWeight(FontWeight.Bold)

        // Precipitation
        composeTestRule.onNodeWithText("Precipitation")
            .assertExists()

        composeTestRule.onNodeWithText("1 mm")
            .assertExists()
            .assertFontWeight(FontWeight.Bold)

        // Humidity
        composeTestRule.onNodeWithText("Humidity")
            .assertExists()

        composeTestRule.onNodeWithText("93%")
            .assertExists()
            .assertFontWeight(FontWeight.Bold)
    }
}
