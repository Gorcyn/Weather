package garcia.ludovic.weather.feature.home

import garcia.ludovic.weather.core.data.model.Forecast

sealed class HomeUiState(
    open val forecast: Forecast? = null,
    open val loading: Boolean = false,
    open val error: Throwable? = null
) {
    object Created : HomeUiState(null, false, null) {

        fun toLoading(): HomeUiState = Loading(forecast = forecast)
    }

    data class Loading(
        override val forecast: Forecast? = null
    ) : HomeUiState(forecast, true, null) {

        fun toSuccess(forecast: Forecast) = Success(forecast)
        fun toError(error: Throwable) = Error(error, forecast)
    }

    data class Success(
        override val forecast: Forecast
    ) : HomeUiState(forecast, false, null) {

        fun toLoading(): HomeUiState = Loading(forecast)
        fun toError(error: Throwable): HomeUiState = Error(error, forecast)
    }

    data class Error(
        override val error: Throwable,
        override val forecast: Forecast? = null
    ) : HomeUiState(forecast, false, error) {

        fun toLoading(): HomeUiState = Loading(forecast)
        fun toError(error: Throwable): HomeUiState = Error(error, forecast)
    }
}
