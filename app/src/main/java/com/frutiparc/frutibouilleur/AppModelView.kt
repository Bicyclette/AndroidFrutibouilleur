package com.frutiparc.frutibouilleur

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.frutiparc.frutibouilleur.ui.theme.BouilleColors

class AppViewModel() : ViewModel() {
    var maleFrutibouille = Frutibouille(
            sexe = mutableStateOf(Sexe.MASCULIN),
            visage = mutableStateOf(Visage.UNIFORME),
            cheveux = mutableStateOf(Cheveux.KASPA),
            yeux = mutableStateOf(Yeux.GROS),
            bouche = mutableStateOf(Bouche.MOYENNE),
            bonnet = mutableStateOf(Bonnet.NONE),
            couleurVisage = mutableStateOf(BouilleColors.H_JAUNE1),
            couleurCheveux = mutableStateOf(BouilleColors.MARRON4),
            couleurYeux = mutableStateOf(BouilleColors.BLEU),
            couleurTetine = mutableStateOf(BouilleColors.H_BLEU2)
        )
    var femaleFrutibouille = Frutibouille(
            sexe = mutableStateOf(Sexe.FEMININ),
            visage = mutableStateOf(Visage.TACHE_ROUSSEUR),
            cheveux = mutableStateOf(Cheveux.CHEVAL),
            yeux = mutableStateOf(Yeux.MANGA_F),
            bouche = mutableStateOf(Bouche.MOYENNE),
            bonnet = mutableStateOf(Bonnet.NONE),
            couleurVisage = mutableStateOf(BouilleColors.PALE3),
            couleurCheveux = mutableStateOf(BouilleColors.H_JAUNE1),
            couleurYeux = mutableStateOf(BouilleColors.BLEU),
            couleurTetine = mutableStateOf(BouilleColors.H_ROSE_BONBON)
        )
    var activeFeature: MutableState<Int> = mutableIntStateOf(0)
    var sexe: MutableState<Sexe> = mutableStateOf<Sexe>(Sexe.MASCULIN)

    var scrollFeatures: ScrollState = ScrollState(0)
    var scrollFeatureOptions: ScrollState = ScrollState(0)
    var scrollColorFace: ScrollState = ScrollState(0)
    var scrollColorHair: ScrollState = ScrollState(0)
    var scrollColorEyes: ScrollState = ScrollState(0)
    var scrollColorPacifier: ScrollState = ScrollState(0)
    var bouilleBoxWidth = mutableStateOf<Int>(0)
    var bouilleBoxHeight = mutableStateOf<Int>(0)
}