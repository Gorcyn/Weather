package garcia.ludovic.weather.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import garcia.ludovic.weather.core.design.theme.WeatherTheme

@Composable
fun Header(
    locality: String,
    colors: HeaderColors
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.containerColor)
            .padding(16.dp)
            .testTag("HEADER_COLUMN")
    ) {
        Text(
            text = locality,
            style = WeatherTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = colors.contentColor
        )
    }
}

data class HeaderColors(val containerColor: Color, val contentColor: Color)

@Composable
@Preview
fun Header_Preview() {
    WeatherTheme {
        Header("Reims", HeaderColors(Color.Blue, Color.White))
    }
}
