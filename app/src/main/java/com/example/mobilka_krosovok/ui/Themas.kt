package org.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mobilka_krosovok.R.font

@Immutable
data class Temka(
    val block: Color,
    val text: Color,
    val sab_text: Color,
    val background: Color,
    val hint: Color
)

@Immutable
data class Shriftik(
    val headingBold32: TextStyle,
    val titleRegular16: TextStyle,
    val bodyRegular14: TextStyle,
    val bodyRegular16: TextStyle,
    val bodyRegular12: TextStyle
)

val localTypograf = staticCompositionLocalOf {
    Shriftik(
        headingBold32 = TextStyle.Default,
        titleRegular16 = TextStyle.Default,
        bodyRegular14 = TextStyle.Default,
        bodyRegular16 = TextStyle.Default,
        bodyRegular12 = TextStyle.Default
    )
}

val fromFontFamily = FontFamily(
    Font(font.roboto_serif, FontWeight.Normal),
    Font(font.roboto_serif_bold, FontWeight.Bold),
    Font(font.roboto_serif_black, FontWeight.Black),
    Font(font.roboto_serif_medium, FontWeight.Medium),
    Font(font.roboto_serif_extrabold, FontWeight.ExtraBold),
    Font(font.roboto_serif_semibold, FontWeight.SemiBold)
)

val localTemka = staticCompositionLocalOf {
    Temka(
        block = Color.Blue,
        text = Color.Blue,
        sab_text = Color.Blue,
        background = Color.Blue,
        hint = Color.Black
    )
}

@Composable
fun Temka(content: @Composable () -> Unit) {
    val temka = Temka(
        block = Color(0xFFFFFFFF),
        text = Color(0xFF2B2B2B),
        sab_text = Color(0xFF707B81),
        background = Color(0xFF7F7F79),
        hint = Color(0xFF6A6A6A)
    )
    val shriftik = Shriftik(
        headingBold32 = TextStyle(fontFamily = fromFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp),
        titleRegular16 = TextStyle(fontFamily = fromFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        bodyRegular14 = TextStyle(fontFamily = fromFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        bodyRegular16 = TextStyle(fontFamily = fromFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        bodyRegular12 = TextStyle(fontFamily = fromFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp)
    )

    CompositionLocalProvider(
        localTemka provides temka,
        localTypograf provides shriftik,
        content = content
    )
}

object MultiTemka {
    val colors: Temka
        @Composable
        get() = localTemka.current
    val typography: Shriftik
        @Composable
        get() = localTypograf.current
}