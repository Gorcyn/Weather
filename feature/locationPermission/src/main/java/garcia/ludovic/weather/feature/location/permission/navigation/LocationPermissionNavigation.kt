package garcia.ludovic.weather.feature.location.permission.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import garcia.ludovic.weather.feature.location.permission.LocationPermissionRoute

internal const val locationPermissionNavigationRoute = "location_permission"

fun NavController.navigateToLocationPermission() = navigate(locationPermissionNavigationRoute)

fun NavGraphBuilder.locationPermissionRoute(onGranted: () -> Unit) {
    composable(
        route = locationPermissionNavigationRoute
    ) {
        LocationPermissionRoute(onGranted)
    }
}
