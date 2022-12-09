package garcia.ludovic.weather.core.network.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import garcia.ludovic.weather.core.network.WeatherNetworkDataSource
import garcia.ludovic.weather.core.network.di.NetworkModule
import garcia.ludovic.weather.core.network.test.TestWeatherNetworkDataSource
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object TestNetworkModule {

    @Provides
    @Singleton
    fun providesWeatherNetworkDataSource(): WeatherNetworkDataSource = TestWeatherNetworkDataSource()
}
