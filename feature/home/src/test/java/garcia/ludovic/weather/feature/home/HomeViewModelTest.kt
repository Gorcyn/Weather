package garcia.ludovic.weather.feature.home

import app.cash.turbine.test
import garcia.ludovic.weather.core.data.test.repository.TestCoarseLocationRepository
import garcia.ludovic.weather.core.data.test.repository.TestForecastRepository
import garcia.ludovic.weather.core.testing.util.DefaultDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = DefaultDispatcherRule()

    private val forecastRepository = TestForecastRepository()
    private val coarseLocationRepository = TestCoarseLocationRepository()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        forecastRepository.fail = false
        coarseLocationRepository.fail = false

        viewModel = HomeViewModel(
            forecastRepository = forecastRepository,
            coarseLocationRepository = coarseLocationRepository
        )
    }

    @Test
    fun `HomeViewModel uiState is initially Created`() = runTest {
        assertEquals(HomeUiState.Created, viewModel.uiState.first())
    }

    @Test
    fun `HomeViewModel uiState is Loading then Success when forecast is successfully retrieved`() = runTest {
        viewModel.uiState.test {
            viewModel.reload()

            assertEquals(HomeUiState.Created, awaitItem())
            assertTrue(awaitItem() is HomeUiState.Loading)
            assertTrue(awaitItem() is HomeUiState.Success)
        }
    }

    @Test
    fun `HomeViewModel uiState is Loading then Error when location fails`() = runTest {
        coarseLocationRepository.fail = true

        viewModel.uiState.test {
            viewModel.reload()

            assertEquals(HomeUiState.Created, awaitItem())
            assertTrue(awaitItem() is HomeUiState.Loading)

            val latest = awaitItem()
            assertTrue(latest is HomeUiState.Error)
            assertEquals("Location not available.", latest.error?.message)
        }
    }

    @Test
    fun `HomeViewModel uiState is Loading then Error when forecast fails`() = runTest {
        forecastRepository.fail = true

        viewModel.uiState.test {
            viewModel.reload()

            assertEquals(HomeUiState.Created, awaitItem())
            assertTrue(awaitItem() is HomeUiState.Loading)

            val latest = awaitItem()
            assertTrue(latest is HomeUiState.Error)
            assertEquals("Network not available.", latest.error?.message)
        }
    }
}
