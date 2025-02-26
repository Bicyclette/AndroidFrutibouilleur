package com.frutiparc.frutibouilleur

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.frutiparc.frutibouilleur.ui.theme.fpDarkGreen
import com.frutiparc.frutibouilleur.ui.theme.fpGreen
import com.frutiparc.frutibouilleur.ui.theme.fpWhitePeach

class Frutibouille(
    var sexe: MutableState<Sexe>,
    var visage: MutableState<Visage>,
    var cheveux: MutableState<Cheveux>,
    var yeux: MutableState<Yeux>,
    var bouche: MutableState<Bouche>,
    var bonnet: MutableState<Bonnet>,
    var couleurVisage: MutableState<Color>,
    var couleurCheveux: MutableState<Color>,
    var couleurYeux: MutableState<Color>,
    var couleurTetine: MutableState<Color>
) {
    fun printFeatures() {
        println("Frutibouille de sexe ${sexe.value.print()}.")
        println("Visage = ${visage.value.print()}.")
        println("Cheveux = ${cheveux.value.print()}.")
        println("Yeux = ${yeux.value.print()}.")
        println("Bouche = ${bouche.value.print()}.")
        println("Bonnet =  ${bonnet.value.print()}.")
    }

    @Composable
    fun Draw(parentBoxWidth: Int, parentBoxHeight: Int) {
        var modifierBox = Modifier.fillMaxHeight(0.75f)
            .background(fpGreen)
            .aspectRatio(1.0f)
            .background(fpWhitePeach)
            .border(2.dp, fpDarkGreen)
        if(parentBoxWidth <= parentBoxHeight) {
            modifierBox = Modifier.fillMaxWidth(0.75f)
                .background(fpGreen)
                .aspectRatio(1.0f)
                .background(fpWhitePeach)
                .border(2.dp, fpDarkGreen)
        }
        Box(modifier = modifierBox,
            contentAlignment = Alignment.Center) {
            val ctx = LocalContext.current
            val paintOver = Paint()
            paintOver.blendMode = BlendMode.SrcOver
            val paintMultiply = Paint()
            paintMultiply.blendMode = BlendMode.Multiply

            Canvas(modifier = Modifier.fillMaxSize()) {
                if (bonnet.value == Bonnet.LVL0) {
                    val imgBMP = loadImageAsBitmap(TexturePath.bonnet[bonnet.value.value], ctx) as ImageBitmap
                    drawImage(imgBMP, dstSize = IntSize(size.width.toInt(), size.height.toInt()))
                } else {
                    DrawLayerBonnetOrHairBack(ctx, this, size)
                    DrawLayerFace(ctx, this, size)
                    DrawLayerEyes(ctx, this, size)
                    DrawLayerMouth(ctx, this, size)
                    DrawLayerBonnetOrHairFront(ctx, this, size)
                }
            }
        }
    }

    private fun DrawLayerBonnetOrHairBack(ctx: Context, drawScope: DrawScope, size: Size) {
        if (bonnet.value != Bonnet.NONE) {
            val imgBMP = loadImageAsBitmap(TexturePath.bonnet[bonnet.value.value + 1], ctx) as ImageBitmap
            drawScope.drawImage(
                imgBMP,
                dstSize = IntSize(size.width.toInt(), size.height.toInt())
            )
        } else {
            if (!Cheveux.entries.any { it.value == (cheveux.value.value + 1) }) {
                val imgBMP = loadImageAsBitmap(TexturePath.cheveux[cheveux.value.value + 1], ctx) as ImageBitmap
                drawScope.drawImage(
                    imgBMP,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                    blendMode = BlendMode.SrcOver,
                    colorFilter = ColorFilter.tint(
                        couleurCheveux.value,
                        blendMode = BlendMode.Modulate
                    )
                )
            }
        }
    }

    private fun DrawLayerFace(ctx: Context, drawScope: DrawScope, size: Size) {
        val imgFaceBMP = loadImageAsBitmap(TexturePath.visage[visage.value.value], ctx) as ImageBitmap
        drawScope.drawImage(
            imgFaceBMP,
            dstSize = IntSize(size.width.toInt(), size.height.toInt()),
            blendMode = BlendMode.SrcOver,
            colorFilter = ColorFilter.tint(
                couleurVisage.value,
                blendMode = BlendMode.Modulate
            )
        )
    }

    private fun DrawLayerEyes(ctx: Context, drawScope: DrawScope, size: Size) {
        if (Yeux.entries.any { it.value == (yeux.value.value + 1) }) {
            // Il n'y a pas de masque
            val imgBMP = loadImageAsBitmap(TexturePath.yeux[yeux.value.value], ctx) as ImageBitmap
            drawScope.drawImage(
                imgBMP,
                dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                blendMode = BlendMode.SrcOver
            )
        } else {
            // Il y a un masque pour définir la couleur de l'iris
            val eyeBMP = loadImageAsBitmap(TexturePath.yeux[yeux.value.value], ctx)
            val maskBMP = loadImageAsBitmap(TexturePath.yeux[yeux.value.value + 1], ctx)
            if (eyeBMP != null && maskBMP != null) {
                val irisBMP = multiplyBitmapByColor(maskBMP, couleurYeux.value)
                drawScope.drawImage(
                    eyeBMP,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                    blendMode = BlendMode.SrcOver
                )
                drawScope.drawImage(
                    irisBMP,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                    blendMode = BlendMode.Modulate
                )
            }
        }
    }

    private fun DrawLayerMouth(ctx: Context, drawScope: DrawScope, size: Size) {
        val imgMouthBMP = loadImageAsBitmap(TexturePath.bouche[bouche.value.value], ctx) as ImageBitmap
        drawScope.drawImage(
            imgMouthBMP,
            dstSize = IntSize(size.width.toInt(), size.height.toInt()),
            blendMode = BlendMode.SrcOver,
            colorFilter = ColorFilter.tint(
                if(bouche.value == Bouche.TETINE){couleurTetine.value}else{couleurVisage.value},
                blendMode = BlendMode.Modulate
            )
        )
        if (bouche.value == Bouche.SOURIRE || bouche.value == Bouche.MACHOIRE) {
            val imgTeethBMP = loadImageAsBitmap(TexturePath.bouche[bouche.value.value + 1], ctx) as ImageBitmap
            drawScope.drawImage(
                imgTeethBMP,
                dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                blendMode = BlendMode.SrcOver
            )
        }
    }

    private fun DrawLayerBonnetOrHairFront(ctx: Context, drawScope: DrawScope, size: Size) {
        if(bonnet.value != Bonnet.NONE) {
            val imgBMP = loadImageAsBitmap(TexturePath.bonnet[bonnet.value.value], ctx) as ImageBitmap
            drawScope.drawImage(
                imgBMP,
                dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                blendMode = BlendMode.SrcOver
            )
        }
        else {
            val imgBMP = loadImageAsBitmap(TexturePath.cheveux[cheveux.value.value], ctx) as ImageBitmap
            drawScope.drawImage(
                imgBMP,
                dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                blendMode = BlendMode.SrcOver,
                colorFilter = ColorFilter.tint(
                    couleurCheveux.value,
                    blendMode = BlendMode.Modulate
                )
            )
        }
    }
}