package garcia.ludovic.weather.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val weatherDispatchers: WeatherDispatchers)

enum class WeatherDispatchers {
    IO
}
