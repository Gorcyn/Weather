package garcia.ludovic.weather.feature.home

import garcia.ludovic.weather.core.data.model.Forecast
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.ApparentTemperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Humidity
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Precipitation
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Temperature
import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.WindSpeed
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeUiStateTest {

    private val forecast = Forecast(
        locality = "Reims",
        weather = "Clouds",
        icon = Forecast.Icon.BROKEN_CLOUDS,
        temperature = Temperature(4.01f, "°C"),
        apparentTemperature = ApparentTemperature(2.76f, "°C"),
        humidity = Humidity(93f, "%"),
        windSpeed = WindSpeed(1.54f, "m/s"),
        precipitation = Precipitation(1.2f, "mm")
    )

    private val exception = Exception("Test exception")

    @Test
    fun `HomeUiState Created transforms to Loading`() {
        assertEquals(HomeUiState.Loading(), HomeUiState.Created.toLoading())
    }

    @Test
    fun `HomeUiState Loading transforms to Success`() {
        assertEquals(HomeUiState.Success(forecast), HomeUiState.Loading().toSuccess(forecast))
    }

    @Test
    fun `HomeUiState Loading transforms to Error`() {
        assertEquals(HomeUiState.Error(exception), HomeUiState.Loading().toError(exception))
    }

    @Test
    fun `HomeUiState Success transforms to Loading`() {
        assertEquals(HomeUiState.Loading(forecast), HomeUiState.Success(forecast).toLoading())
    }

    @Test
    fun `HomeUiState Success transforms to Error`() {
        assertEquals(HomeUiState.Error(exception, forecast), HomeUiState.Success(forecast).toError(exception))
    }

    @Test
    fun `HomeUiState Error transforms to Loading`() {
        assertEquals(HomeUiState.Loading(), HomeUiState.Error(exception).toLoading())
    }

    @Test
    fun `HomeUiState Error transforms to Error`() {
        val anotherException = Exception("Another test exception")
        assertEquals(HomeUiState.Error(anotherException), HomeUiState.Error(exception).toError(anotherException))
    }
}
