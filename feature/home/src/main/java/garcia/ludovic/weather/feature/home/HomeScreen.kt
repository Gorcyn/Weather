package garcia.ludovic.weather.feature.home

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.SystemUiController
import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.design.theme.LocalSnackbarHostState
import garcia.ludovic.weather.core.design.theme.LocalSystemUiController
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.resources.color
import garcia.ludovic.weather.feature.home.resources.onColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    configuration: Configuration = LocalConfiguration.current,
    systemUiController: SystemUiController? = LocalSystemUiController.current,
    navigateToLocationPermission: () -> Unit
) {
    val locationPermissionState = rememberPermissionState(ACCESS_COARSE_LOCATION)
    if (!locationPermissionState.status.isGranted) {
        navigateToLocationPermission()
    }

    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (uiState.value is HomeUiState.Created) {
            viewModel.reload()
        }
    }

    uiState.value.error?.let {
        val snackbarHostState = LocalSnackbarHostState.current
        coroutineScope.launch {
            // TODO: Remap error to actual messages
            snackbarHostState.showSnackbar(it.message ?: it.toString())
        }
    }

    HomeScreen(
        forecast = uiState.value.forecast,
        loading = uiState.value.loading,
        onRefresh = { viewModel.reload() },
        configuration,
        systemUiController
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    forecast: Forecast?,
    loading: Boolean,
    onRefresh: () -> Unit,
    configuration: Configuration = LocalConfiguration.current,
    systemUiController: SystemUiController? = LocalSystemUiController.current
) {
    // States
    val swipeRefreshState = rememberPullRefreshState(loading, onRefresh)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(swipeRefreshState)
    ) {
        when (configuration.orientation) {
            ORIENTATION_LANDSCAPE -> {
                if (forecast != null) {
                    val containerColor = forecast.temperature.color()
                    val contentColor = containerColor.onColor()

                    systemUiController?.setStatusBarColor(
                        containerColor,
                        containerColor.onColor() == Color.Black
                    )
                    systemUiController?.setNavigationBarColor(
                        containerColor,
                        containerColor.onColor() == Color.Black
                    )

                    ForecastLandscape(
                        forecast,
                        containerColor,
                        contentColor,
                        scrollState
                    )
                }
            }
            else -> {
                if (forecast != null) {
                    val containerColor = forecast.temperature.color()
                    val contentColor = containerColor.onColor()

                    systemUiController?.setStatusBarColor(
                        containerColor,
                        containerColor.onColor() == Color.Black
                    )
                    systemUiController?.setNavigationBarColor(
                        WeatherTheme.colorScheme.background,
                        WeatherTheme.colorScheme.background.onColor() == Color.Black
                    )
                    ForecastPortrait(
                        forecast,
                        containerColor,
                        contentColor,
                        scrollState
                    )
                }
            }
        }
        PullRefreshIndicator(
            loading,
            swipeRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
@Preview
fun HomeScreen_Forecast_Preview() {
    WeatherTheme {
        Surface(Modifier.background(WeatherTheme.colorScheme.surface)) {
            HomeScreen(
                Forecast(
                    "Reims",
                    "Clouds",
                    Forecast.Icon.BROKEN_CLOUDS,
                    Forecast.ValueWithUnit.Temperature(4.01f, "°C"),
                    Forecast.ValueWithUnit.ApparentTemperature(2.76f, "°C"),
                    Forecast.ValueWithUnit.Humidity(93f, "%"),
                    Forecast.ValueWithUnit.WindSpeed(1.54f, "m/s"),
                    Forecast.ValueWithUnit.Precipitation(3.16f, "mm")
                ),
                loading = false,
                onRefresh = {},
                Configuration().apply { orientation = ORIENTATION_PORTRAIT },
                null
            )
        }
    }
}

@Composable
@Preview
fun HomeScreen_NoForecast_Preview() {
    WeatherTheme {
        Surface(Modifier.background(WeatherTheme.colorScheme.surface)) {
            HomeScreen(
                forecast = null,
                loading = true,
                onRefresh = {},
                Configuration().apply { orientation = ORIENTATION_PORTRAIT },
                null
            )
        }
    }
}
