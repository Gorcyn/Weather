package garcia.ludovic.weather.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import garcia.ludovic.weather.core.network.BuildConfig
import garcia.ludovic.weather.core.network.WeatherNetworkDataSource
import garcia.ludovic.weather.core.network.model.NetworkForecast
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitWeatherNetworkApi {
    @GET("/data/2.5/weather?units=metric&lang=en")
    suspend fun getForecast(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") startDate: String
    ): NetworkForecast
}

private const val API_URL: String = BuildConfig.API_URL
private const val API_KEY: String = BuildConfig.API_KEY

@Singleton
class RetrofitWeatherNetwork @Inject constructor(
    networkJson: Json
) : WeatherNetworkDataSource {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val api = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        )
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(RetrofitWeatherNetworkApi::class.java)

    override suspend fun getForecast(latitude: Float, longitude: Float): NetworkForecast {
        return api.getForecast(latitude, longitude, API_KEY)
    }
}
