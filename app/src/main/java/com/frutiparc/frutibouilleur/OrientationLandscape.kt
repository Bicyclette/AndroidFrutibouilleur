package com.frutiparc.frutibouilleur

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
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
fun DrawLandscape(
    constraints: ConstraintSet,
    viewModel: AppViewModel
) {
    ConstraintLayout(
        constraints,
        modifier = Modifier
            .fillMaxSize()
    ) {
        PrintFeatures(viewModel, textSize = 12.sp, modifier = Modifier.background(fpDarkGreen).layoutId("features"))

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
            DrawFaceColorSelectionLandscape(viewModel)
        }
        else if(viewModel.activeFeature.value == 2) {
            DrawHairColorSelectionLandscape(viewModel)
        }
        else if(viewModel.activeFeature.value == 3) {
            DrawEyesColorSelectionLandscape(viewModel)
        }
        else if((viewModel.sexe.value == Sexe.MASCULIN && viewModel.activeFeature.value == 4 && viewModel.maleFrutibouille.bouche.value == Bouche.TETINE)
            || (viewModel.sexe.value == Sexe.FEMININ && viewModel.activeFeature.value == 4 && viewModel.femaleFrutibouille.bouche.value == Bouche.TETINE)) {
            DrawPacifierColorSelectionLandscape(viewModel)
        }
        else {
            Column(modifier = Modifier
                .background(fpGreen)
                .layoutId("color")
                .border(2.dp, fpDarkGreen)
            ) {}
        }
    }
}

@Composable
fun DrawFaceColorSelectionLandscape(viewModel: AppViewModel) {
    val colorList = BouilleColors.skin + BouilleColors.hair
    Column(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .verticalScroll(viewModel.scrollColorFace),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
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
fun DrawHairColorSelectionLandscape(viewModel: AppViewModel) {
    val colors = listOf(BouilleColors.MARRON1, BouilleColors.MARRON2, BouilleColors.MARRON3, BouilleColors.MARRON4) + BouilleColors.hair
    Column(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .verticalScroll(viewModel.scrollColorHair),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
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
fun DrawEyesColorSelectionLandscape(viewModel: AppViewModel) {
    Column(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .verticalScroll(viewModel.scrollColorEyes),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
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
fun DrawPacifierColorSelectionLandscape(viewModel: AppViewModel) {
    Column(modifier = Modifier
        .background(fpGreen)
        .layoutId("color")
        .border(2.dp, fpDarkGreen)
        .verticalScroll(viewModel.scrollColorPacifier),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
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