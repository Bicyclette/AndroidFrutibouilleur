package com.frutiparc.frutibouilleur

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import java.io.InputStream

fun loadImageAsBitmap(path: String, ctx: Context) : ImageBitmap? {
    try {
        val cleanPath = path.removePrefix("file:///android_asset/")
        val istream: InputStream = ctx.assets.open(cleanPath)
        val bitmap: Bitmap? = BitmapFactory.decodeStream(istream)
        if(bitmap != null) {
            return bitmap.asImageBitmap()
        }
        return null
    }
    catch(e: Exception) {
        println("An error occurred during bitmap image loading")
        return null
    }
}

fun multiplyBitmapByColor(img: ImageBitmap, color: Color): ImageBitmap {
    val bitmap = img.asAndroidBitmap()
    val width = bitmap.width
    val height = bitmap.height
    val targetColor = color.toArgb() // (32 bits Int) chaque composante est sur 8 bits (256 valeurs de 0 à 255)
    val pixels = IntArray(width * height)
    bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

    for(i in pixels.indices) {
        val pixel = pixels[i]
        var r = (pixel.red / 255.0f) * (targetColor.red / 255.0f)
        var g = (pixel.green / 255.0f) * (targetColor.green / 255.0f)
        var b = (pixel.blue / 255.0f) * (targetColor.blue / 255.0f)
        var alpha = pixel.alpha / 255.0f
        if(alpha == 0.0f) {
            r = 1.0f
            g = 1.0f
            b = 1.0f
            alpha = 1.0f
        }
        var multipliedColor = Color(r, g, b, alpha)
        pixels[i] = multipliedColor.toArgb()
    }

    return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888).asImageBitmap()
}