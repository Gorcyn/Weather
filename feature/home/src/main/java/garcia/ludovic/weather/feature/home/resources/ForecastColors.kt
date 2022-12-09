package garcia.ludovic.weather.feature.home.resources

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Temperature

fun Temperature.color(): Color {
    return when {
        value <= 10 -> Color(0, value.coerceAtLeast(0f).toInt() * 25, 255)
        value in 10f..15f -> Color(0, 255, (255 - ((value.toInt() - 10) * 51)))
        value in 15f..20f -> Color((value.toInt() - 15) * 51, 255, 0)
        value in 20f..40f -> Color(255, 255 - ((value.toInt() - 20) * 12), 0)
        else -> Color(255, 0, 0)
    }
}

fun Color.onColor(): Color = if (luminance() > 0.5f) {
    Color.Black
} else {
    Color.White
}
