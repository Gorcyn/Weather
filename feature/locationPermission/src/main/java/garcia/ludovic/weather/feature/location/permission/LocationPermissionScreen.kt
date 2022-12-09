package garcia.ludovic.weather.feature.location.permission

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import garcia.ludovic.weather.core.design.theme.WeatherTheme

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionRoute(
    onGranted: () -> Unit
) {
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_COARSE_LOCATION)

    if (locationPermissionState.status.isGranted) {
        onGranted()
    } else {
        LocationPermissionScreen(locationPermissionState.status.shouldShowRationale) {
            locationPermissionState.launchPermissionRequest()
        }
    }
}

@Composable
fun LocationPermissionScreen(
    shouldShowRationale: Boolean,
    launchPermissionRequest: () -> Unit
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.location_95366)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(WeatherTheme.colorScheme.background)
            .padding(32.dp)
    ) {
        val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        val textToShow = if (shouldShowRationale) {
            R.string.location_request_rationale
        } else {
            R.string.location_request
        }
        Text(
            stringResource(textToShow),
            color = WeatherTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Button(onClick = { launchPermissionRequest() }) {
            Text(stringResource(R.string.location_request_action))
        }
    }
}

@Composable
@Preview
fun LocationPermissionScreen_Rationale_Preview() {
    WeatherTheme {
        LocationPermissionScreen(true) {}
    }
}

@Composable
@Preview
fun LocationPermissionScreen_Preview() {
    WeatherTheme {
        LocationPermissionScreen(false) {}
    }
}
