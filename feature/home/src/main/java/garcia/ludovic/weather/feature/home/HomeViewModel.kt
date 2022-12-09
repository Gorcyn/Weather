package garcia.ludovic.weather.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import garcia.ludovic.weather.core.data.repository.CoarseLocationRepository
import garcia.ludovic.weather.core.data.repository.ForecastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InvalidObjectException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository,
    private val coarseLocationRepository: CoarseLocationRepository
) : ViewModel() {

    private val internalUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Created)
    val uiState: StateFlow<HomeUiState> = internalUiState

    fun reload() {
        viewModelScope.launch {
            internalUiState.emit(
                when (val state = uiState.value) {
                    HomeUiState.Created -> (state as HomeUiState.Created).toLoading()
                    is HomeUiState.Loading -> state // Already loading
                    is HomeUiState.Success -> state.toLoading()
                    is HomeUiState.Error -> state.toLoading()
                }
            )

            try {
                val location = coarseLocationRepository.getCurrentLocation()
                    ?: throw InvalidObjectException("Location not available.")
                val forecast = forecastRepository.getForecast(location)
                internalUiState.emit(
                    when (val state = uiState.value) {
                        is HomeUiState.Loading -> state.toSuccess(forecast)
                        else -> HomeUiState.Success(forecast)
                    }
                )
            } catch (error: Throwable) {
                internalUiState.emit(
                    when (val state = uiState.value) {
                        is HomeUiState.Success -> state.toError(error)
                        is HomeUiState.Error -> state.toError(error)
                        is HomeUiState.Loading -> state.toError(error)
                        else -> HomeUiState.Error(error)
                    }
                )
            }
        }
    }
}
