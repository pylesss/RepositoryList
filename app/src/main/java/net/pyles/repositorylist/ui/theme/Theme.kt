package net.pyles.repositorylist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColors(
    primary = Purple80,
    primaryVariant = Pink80,
    secondary = PurpleGrey80
)

private val LightColorScheme = lightColors(
    primary = Purple40,
    primaryVariant = Pink40,
    secondary = PurpleGrey40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun RepositoryListTheme(
    darkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    MaterialTheme(
        colors =  colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}