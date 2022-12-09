package garcia.ludovic.weather.core.design.theme

import androidx.compose.runtime.compositionLocalOf
import com.google.accompanist.systemuicontroller.SystemUiController

val LocalSystemUiController = compositionLocalOf<SystemUiController> {
    error("No SystemUiController provided")
}
