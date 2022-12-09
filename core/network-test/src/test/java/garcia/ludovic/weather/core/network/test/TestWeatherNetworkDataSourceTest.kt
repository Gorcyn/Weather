package garcia.ludovic.weather.core.network.test

import garcia.ludovic.weather.core.network.model.NetworkIcon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TestWeatherNetworkDataSourceTest {

    private val testWeatherNetwork = TestWeatherNetworkDataSource()

    @Test
    fun `getForecast gets a test forecast`() = runTest {
        val forecast = testWeatherNetwork.getForecast(49.258f, 4.031f)

        assertEquals(1, forecast.weather.count())
        assertEquals("Clouds", forecast.weather.first().main)
        assertEquals(NetworkIcon.BROKEN_CLOUDS, forecast.weather.first().icon)

        assertEquals(4.01f, forecast.main.temp)
        assertEquals(2.76f, forecast.main.feels_like)
        assertEquals(93f, forecast.main.humidity)
        assertEquals(1.54f, forecast.wind.speed)
        assertEquals(1.2f, forecast.rain?.volume_1h)

        assertEquals("Reims", forecast.name)
    }
}
