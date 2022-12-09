package garcia.ludovic.weather.core.design.test

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteraction

fun SemanticsNodeInteraction.assertBackgroundColor(color: Color): SemanticsNodeInteraction {
    if (!hasBackgroundColor(color)) {
        throw AssertionError("Assert failed: The component does not have background color! $color")
    }
    return this
}

private fun SemanticsNodeInteraction.hasBackgroundColor(color: Color): Boolean {
    return fetchSemanticsNode().layoutInfo.getModifierInfo().filter { modifierInfo ->
        modifierInfo.modifier == Modifier.background(color)
    }.size == 1
}
