package garcia.ludovic.weather.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import garcia.ludovic.weather.core.data.repository.CoarseLocationRepository
import garcia.ludovic.weather.core.data.repository.DefaultCoarseLocationRepository
import garcia.ludovic.weather.core.data.repository.DefaultForecastRepository
import garcia.ludovic.weather.core.data.repository.ForecastRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsForecastRepository(
        forecastRepository: DefaultForecastRepository
    ): ForecastRepository

    @Binds
    fun bindsCoarseLocationRepository(
        coarseLocationRepository: DefaultCoarseLocationRepository
    ): CoarseLocationRepository
}
