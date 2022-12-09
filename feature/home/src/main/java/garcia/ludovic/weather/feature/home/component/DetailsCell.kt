package garcia.ludovic.weather.feature.home.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit
import garcia.ludovic.weather.core.design.theme.WeatherTheme
import garcia.ludovic.weather.feature.home.R

@Composable
fun DetailsCell(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    valueWithUnit: ValueWithUnit?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp, 8.dp)
    ) {
        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(48.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = WeatherTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .size(48.dp)
                            .background(WeatherTheme.colorScheme.surfaceVariant)
                            .padding(8.dp)
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = title),
                style = WeatherTheme.typography.bodyMedium
            )
            val value = valueWithUnit?.run { stringResource(id = format, value, unit) } ?: "-"
            Text(
                text = value,
                style = WeatherTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun DetailsCell_Preview() {
    WeatherTheme {
        DetailsCell(
            R.string.weather_now_apparent,
            R.drawable.ic_outline_thermostat_24,
            ValueWithUnit.Temperature(42f, "°C")
        )
    }
}
