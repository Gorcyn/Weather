package garcia.ludovic.weather.core.network.model.serializer

import garcia.ludovic.weather.core.network.model.NetworkIcon
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkIconSerializerTest {

    private val serializer = NetworkIconSerializer()

    @Test
    fun `NetworkIconSerializer deserializes`() {
        val asIcon = NetworkIcon.CLEAR_SKY
        val asString = """"01d""""
        assertEquals(asIcon, Json.decodeFromString(serializer, asString))
    }

    @Test
    fun `NetworkIconSerializer deserializes night`() {
        val asIcon = NetworkIcon.CLEAR_SKY
        val asString = """"01n""""
        assertEquals(asIcon, Json.decodeFromString(serializer, asString))
    }

    @Test
    fun `NetworkIconSerializer serializes`() {
        val asIcon = NetworkIcon.CLEAR_SKY
        val asString = """"01d""""
        assertEquals(asString, Json.encodeToString(serializer, asIcon))
    }
}
