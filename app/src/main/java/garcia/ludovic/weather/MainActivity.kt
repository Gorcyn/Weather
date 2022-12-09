package garcia.ludovic.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import garcia.ludovic.weather.core.design.theme.LocalSnackbarHostState
import garcia.ludovic.weather.core.design.theme.LocalSystemUiController
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.navigation.homeGraph
import garcia.ludovic.weather.feature.home.navigation.homeGraphRoutePattern
import garcia.ludovic.weather.feature.home.navigation.navigateToHome
import garcia.ludovic.weather.feature.location.permission.navigation.locationPermissionRoute
import garcia.ludovic.weather.feature.location.permission.navigation.navigateToLocationPermission

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                val systemUiController = rememberSystemUiController()
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()

                CompositionLocalProvider(
                    LocalSnackbarHostState provides snackbarHostState,
                    LocalSystemUiController provides systemUiController
                ) {
                    Scaffold(
                        containerColor = WeatherTheme.colorScheme.background,
                        contentColor = WeatherTheme.colorScheme.onBackground,
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = homeGraphRoutePattern
                            ) {
                                homeGraph(
                                    navigateToLocationPermission = {
                                        navController.navigateToLocationPermission()
                                    }
                                ) {
                                    locationPermissionRoute {
                                        navController.backQueue.clear()
                                        navController.navigateToHome()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
