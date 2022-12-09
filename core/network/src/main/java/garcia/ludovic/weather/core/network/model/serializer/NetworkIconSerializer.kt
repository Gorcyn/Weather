package garcia.ludovic.weather.core.network.model.serializer

import garcia.ludovic.weather.core.network.model.NetworkIcon
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

// TODO: Support night and day
private const val CODE_CHARACTERS_TO_TAKE = 2

class NetworkIconSerializer : KSerializer<NetworkIcon> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Icon", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): NetworkIcon =
        decoder.decodeString().take(CODE_CHARACTERS_TO_TAKE).run {
            NetworkIcon.values().first { it.code == this }
        }

    override fun serialize(encoder: Encoder, value: NetworkIcon) =
        encoder.encodeString("${value.code}d")
}
