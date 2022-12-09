package garcia.ludovic.weather.core.data.repository

import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import garcia.ludovic.weather.core.common.Dispatcher
import garcia.ludovic.weather.core.common.WeatherDispatchers
import garcia.ludovic.weather.core.data.model.Location
import garcia.ludovic.weather.core.data.model.asLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCoarseLocationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    @Dispatcher(WeatherDispatchers.IO) private val defaultDispatcher: CoroutineDispatcher
) : CoarseLocationRepository {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation(): Location? = withContext(defaultDispatcher) {
        val cancellationTokenSource = CancellationTokenSource()
        try {
            fusedLocationClient
                .getCurrentLocation(Priority.PRIORITY_LOW_POWER, cancellationTokenSource.token)
                .await(cancellationTokenSource)
                .asLocal()
        } catch (e: SecurityException) {
            Log.w("LocationApi", "Couldn't get location, did you request location permissions?", e)
            null
        }
    }
}
