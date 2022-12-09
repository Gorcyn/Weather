package garcia.ludovic.weather.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import garcia.ludovic.weather.core.network.WeatherNetworkDataSource
import garcia.ludovic.weather.core.network.retrofit.RetrofitWeatherNetwork
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesWeatherNetworkDataSource(
        networkJson: Json
    ): WeatherNetworkDataSource = RetrofitWeatherNetwork(networkJson)
}
