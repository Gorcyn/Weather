package garcia.ludovic.weather.feature.home.resources

import garcia.ludovic.weather.core.data.model.Forecast.ValueWithUnit.Temperature
import org.junit.Assert.assertEquals
import org.junit.Test

class ForecastColorsTest {

    @Test
    fun `Temperature under 0 gives blue`() {
        val temperature = Temperature(-1f, "°C")
        val color = temperature.color()

        assertEquals(0f, color.red)
        assertEquals(0f, color.green)
        assertEquals(255f / 255f, color.blue)
    }

    @Test
    fun `Temperature in 0_10 gives blue with a bit of green`() {
        val temperature = Temperature(5f, "°C")
        val color = temperature.color()

        assertEquals(0f, color.red)
        assertEquals(125f / 255f, color.green)
        assertEquals(255f / 255f, color.blue)
    }

    @Test
    fun `Temperature in 10_15 gives green with a bit of blue`() {
        val temperature = Temperature(12f, "°C")
        val color = temperature.color()

        assertEquals(0f, color.red)
        assertEquals(255f / 255f, color.green)
        assertEquals(153f / 255f, color.blue)
    }

    @Test
    fun `Temperature in 15_20 gives green with a bit of red`() {
        val temperature = Temperature(18f, "°C")
        val color = temperature.color()

        assertEquals(153f / 255f, color.red)
        assertEquals(255f / 255f, color.green)
        assertEquals(0f, color.blue)
    }

    @Test
    fun `Temperature in 20_40 gives red with a bit of green`() {
        val temperature = Temperature(30f, "°C")
        val color = temperature.color()

        assertEquals(255f / 255f, color.red)
        assertEquals(135f / 255f, color.green)
        assertEquals(0f, color.blue)
    }

    @Test
    fun `Temperature above 40 gives red`() {
        val temperature = Temperature(41f, "°C")
        val color = temperature.color()

        assertEquals(255f / 255f, color.red)
        assertEquals(0f, color.green)
        assertEquals(0f, color.blue)
    }
}
