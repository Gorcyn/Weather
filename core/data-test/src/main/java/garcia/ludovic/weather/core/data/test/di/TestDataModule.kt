package garcia.ludovic.weather.core.data.test.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import garcia.ludovic.weather.core.data.di.DataModule
import garcia.ludovic.weather.core.data.repository.CoarseLocationRepository
import garcia.ludovic.weather.core.data.repository.ForecastRepository
import garcia.ludovic.weather.core.data.test.repository.TestCoarseLocationRepository
import garcia.ludovic.weather.core.data.test.repository.TestForecastRepository

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface TestDataModule {

    @Binds
    fun bindsForecastRepository(
        forecastRepository: TestForecastRepository
    ): ForecastRepository

    @Binds
    fun bindsCoarseLocationRepository(
        coarseLocationRepository: TestCoarseLocationRepository
    ): CoarseLocationRepository
}
