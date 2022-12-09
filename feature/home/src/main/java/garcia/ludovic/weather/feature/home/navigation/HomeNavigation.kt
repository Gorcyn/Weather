package garcia.ludovic.weather.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import garcia.ludovic.weather.feature.home.HomeRoute

const val homeGraphRoutePattern = "home_graph"
const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeGraph(
    navigateToLocationPermission: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = homeNavigationRoute
    ) {
        composable(route = homeNavigationRoute) {
            HomeRoute(navigateToLocationPermission = navigateToLocationPermission)
        }
        nestedGraphs()
    }
}
