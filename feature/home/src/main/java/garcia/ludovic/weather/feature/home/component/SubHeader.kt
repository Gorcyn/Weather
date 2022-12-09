package garcia.ludovic.weather.feature.home.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.R

@Composable
fun SubHeader(
    @StringRes title: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WeatherTheme.colorScheme.background)
            .padding(16.dp)
            .testTag("SUB_HEADER_COLUMN")
    ) {
        Text(
            text = stringResource(title),
            color = WeatherTheme.colorScheme.onBackground,
            style = WeatherTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun SubHeader_Preview() {
    WeatherTheme {
        SubHeader(R.string.weather_now_title)
    }
}
