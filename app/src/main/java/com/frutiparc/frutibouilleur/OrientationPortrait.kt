package com.frutiparc.frutibouilleur

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import com.frutiparc.frutibouilleur.ui.theme.BouilleColors
import com.frutiparc.frutibouilleur.ui.theme.fpDarkGreen
import com.frutiparc.frutibouilleur.ui.theme.fpGreen

@Composable
fun DrawPortrait(
    constraints: ConstraintSet,
    viewModel: AppViewModel
) {
    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxSize()
    ) {
        PrintFeatures(viewModel, 12.sp, modifier = Modifier.background(fpDarkGreen).layoutId("features"))

        Box(modifier = Modifier
            .fillMaxSize()
            .background(fpGreen)
            .layoutId("bouille")
            .border(2.dp, fpDarkGreen)
            .onGloballyPositioned { coords ->
                viewModel.bouilleBoxWidth.value = coords.size.width
                viewModel.bouilleBoxHeight.value = coords.size.height
            },
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.sexe.value == Sexe.MASCULIN) {
                viewModel.maleFrutibouille.Draw(viewModel.bouilleBoxWidth.value, viewModel.bouilleBoxHeight.value)
            } else {
                viewModel.femaleFrutibouille.Draw(viewModel.bouilleBoxWidth.value, viewModel.bouilleBoxHeight.value)
            }
        }

        if(viewModel.activeFeature.value == 1) {
            DrawFaceColorSelectionPortrait(viewModel)
        }
        else if(viewModel.activeFeature.value == 2) {
            DrawHairColorSelectionPortrait(viewModel)
        }
        else if(viewModel.activeFeature.value == 3) {
            DrawEyesColorSelectionPortrait(viewModel)
        }
        else if((viewModel.sexe.value == Sexe.MASCULIN && viewModel.activeFeature.value == 4 && viewModel.maleFrutibouille.bouche.value == Bouche.TETINE)
            || (viewModel.sexe.value == Sexe.FEMININ && viewModel.activeFeature.value == 4 && viewModel.femaleFrutibouille.bouche.value == Bouche.TETINE)) {
            DrawPacifierColorSelectionPortrait(viewModel)
        }
        else {
            Row(modifier = Modifier
                .background(fpGreen)
                .layoutId("color")
                .border(2.dp, fpDarkGreen)
            ) {}
        }
    }
}

@Composable
fun DrawFaceColorSelectionPortrait(viewModel: AppViewModel) {
    val colorList = BouilleColors.skin + BouilleColors.hair
    Row(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .horizontalScroll(viewModel.scrollColorFace),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(c in colorList) {
            Box(modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .border(border = BorderStroke(width = 2.dp, color = Color.Gray))
                .fillMaxHeight(0.7f)
                .aspectRatio(1.0f)
                .background(c)
                .clickable {
                    if(viewModel.sexe.value == Sexe.MASCULIN) {
                        viewModel.maleFrutibouille.couleurVisage.value = c
                    }
                    else {
                        viewModel.femaleFrutibouille.couleurVisage.value = c
                    }
                }){}
        }
    }
}

@Composable
fun DrawHairColorSelectionPortrait(viewModel: AppViewModel) {
    val colors = listOf(BouilleColors.MARRON1, BouilleColors.MARRON2, BouilleColors.MARRON3, BouilleColors.MARRON4) + BouilleColors.hair
    Row(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .horizontalScroll(viewModel.scrollColorHair),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (c in colors) {
            Box(modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .border(border = BorderStroke(width = 2.dp, color = Color.Gray))
                .fillMaxHeight(0.7f)
                .aspectRatio(1.0f)
                .background(c)
                .clickable {
                    if (viewModel.sexe.value == Sexe.MASCULIN) {
                        viewModel.maleFrutibouille.couleurCheveux.value = c
                    } else {
                        viewModel.femaleFrutibouille.couleurCheveux.value = c
                    }
                }) {}
        }
    }
}

@Composable
fun DrawEyesColorSelectionPortrait(viewModel: AppViewModel) {
    Row(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .horizontalScroll(viewModel.scrollColorEyes),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (c in BouilleColors.eyes) {
            Box(modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .border(border = BorderStroke(width = 2.dp, color = Color.Gray))
                .fillMaxHeight(0.7f)
                .aspectRatio(1.0f)
                .background(c)
                .clickable {
                    if (viewModel.sexe.value == Sexe.MASCULIN) {
                        viewModel.maleFrutibouille.couleurYeux.value = c
                    } else {
                        viewModel.femaleFrutibouille.couleurYeux.value = c
                    }
                }) {}
        }
    }
}

@Composable
fun DrawPacifierColorSelectionPortrait(viewModel: AppViewModel) {
    Row(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .horizontalScroll(viewModel.scrollColorPacifier),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (c in BouilleColors.hair) {
            Box(modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .border(border = BorderStroke(width = 2.dp, color = Color.Gray))
                .fillMaxHeight(0.7f)
                .aspectRatio(1.0f)
                .background(c)
                .clickable {
                    if (viewModel.sexe.value == Sexe.MASCULIN) {
                        viewModel.maleFrutibouille.couleurTetine.value = c
                    } else {
                        viewModel.femaleFrutibouille.couleurTetine.value = c
                    }
                }) {}
        }
    }
}