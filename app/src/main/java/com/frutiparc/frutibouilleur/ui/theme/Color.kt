package com.frutiparc.frutibouilleur.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val fpGreen = Color(0xFFACE66C)
val fpDarkGreen = Color(0xFF6EB72A)
val fpWhitePeach = Color(0xFFE8F4D0)
val fpYellow = Color(0xFFFCE903)

object BouilleColors {
    // ========== eye colors
    val JAUNE	= Color.hsv(53.0f, 0.95f, 1.0f)
    val MARRON	= Color.hsv(25.0f, 0.95f, 0.4f)
    val ROUGE	= Color.hsv(360.0f, 1.0f, 1.0f)
    val VIOLET	= Color.hsv(275.0f, 1.0f, 1.0f)
    val ROSE	= Color.hsv(322.0f, 1.0f, 1.0f)
    val BLEU	= Color.hsv(216.0f, 1.0f, 1.0f)
    val VERT	= Color.hsv(100.0f, 1.0f, 1.0f)
    val BLACK	= Color.hsv(0.0f, 0.0f, 0.0f)

    val eyes = listOf(JAUNE, MARRON, ROUGE, VIOLET, ROSE, BLEU, VERT, BLACK)

    // ========== skin colors
    val PALE1           = Color(0.988f, 0.863f, 0.847f)
    val PALE2           = Color(0.984f, 0.784f, 0.745f)
    val PALE3           = Color(0.98f, 0.706f, 0.643f)
    val MARRON_CLAIR1   = Color(0.902f, 0.608f, 0.314f)
    val MARRON_CLAIR2   = Color(0.843f, 0.49f, 0.235f)
    val MARRON_CLAIR3   = Color(0.784f, 0.392f, 0.157f)
    val MARRON1         = Color(0.627f, 0.392f, 0.176f)
    val MARRON2         = Color(0.541f, 0.341f, 0.145f)
    val MARRON3         = Color(0.424f, 0.267f, 0.118f)
    val MARRON4         = Color(0.294f, 0.188f, 0.078f)

    val skin = listOf(PALE1, PALE2, PALE3, MARRON_CLAIR1, MARRON_CLAIR2, MARRON_CLAIR2, MARRON_CLAIR3, MARRON1, MARRON2, MARRON3, MARRON4)

    // ========== hair colors
    val H_ROUGE         = Color(0.946f, 0.046f, 0.05f)
    val H_ORANGE        = Color(0.78f, 0.31f, 0.15f)
    val H_JAUNE1        = Color(1.0f, 0.851f, 0.0f)
    val H_JAUNE2        = Color(0.901f, 0.704f, 0.011f)
    val H_VERT1         = Color(0.097f, 0.932f, 0.023f)
    val H_VERT2         = Color(0.004f, 0.586f, 0.004f)
    val H_BLEU1         = Color(0.219f, 0.917f, 1.0f)
    val H_BLEU2         = Color(0.0f, 0.339f, 1.0f)
    val H_BLEU3         = Color(0.317f, 0.152f, 0.925f)
    val H_POURPRE       = Color(0.423f, 0.050f, 0.431f)
    val H_VIOLET        = Color(1.0f, 0.0f, 0.838f)
    val H_ROSE          = Color(0.964f, 0.447f, 0.88f)
    val H_ROSE_BONBON   = Color(1.0f, 0.078f, 0.529f)
    val H_BLACK         = Color(0.135f, 0.053f, 0.008f)

    val hair = listOf(H_ROUGE, H_ORANGE, H_JAUNE1, H_JAUNE2, H_VERT1, H_VERT2, H_BLEU1, H_BLEU2, H_BLEU3, H_POURPRE, H_VIOLET, H_ROSE, H_ROSE_BONBON, H_BLACK)
}