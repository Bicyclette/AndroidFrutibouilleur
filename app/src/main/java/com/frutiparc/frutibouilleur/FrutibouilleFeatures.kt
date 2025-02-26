package com.frutiparc.frutibouilleur

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.frutiparc.frutibouilleur.ui.theme.fpDarkGreen
import com.frutiparc.frutibouilleur.ui.theme.fpGreen
import com.frutiparc.frutibouilleur.ui.theme.fpWhitePeach

enum class Sexe(val value: Int) {
    MASCULIN(0),
    FEMININ(1)
}

enum class Visage(val value: Int) {
    UNIFORME(0),
    TACHE_ROUSSEUR(1)
}

enum class Cheveux(val value: Int) {
    MIXTE(0),
    // ========== femme
    ANNEE_70(22),
    COUETTES(28),
    FRANGE(32),
    AU_BOL(24),
    DECOIFFE_F(30),
    CHEVAL(36),
    MICKEY(26),
    TRESSES(42),
    MI_LONG(34),
    RAIE(38),
    MEDUSE(40),
    // ========== homme
    KASPA(20),
    CULBUR(4),
    HIGEM(13),
    ELVIS(7),
    HERISSON(12),
    MANGA(15),
    ARRIERE(2),
    BULBE(3),
    CRETE(18),
    EPI1(8),
    EPI2(10),
    EPI3(11),
    DECOIFFE_H(6),
    MECHE_AVANT(16),
    STEADY(17)
}

enum class Yeux(val value: Int) {
    COLERE_F(11),
    COLERE_H(6),
    FATIGUE(2),
    INQUIET(14),
    MANGA_F(15),
    MANGA_H(9),
    AMANDE(4),
    GROS(7),
    EYELINER(12),
    INSOMNIE(0)
}

enum class Bouche(val value: Int) {
    TETINE(7),
    SOURIRE(3),
    PETITE(0),
    MOYENNE(1),
    GRANDE(2),
    MACHOIRE(5)
}

enum class Bonnet(val value: Int) {
    LVL0(0),
    LVL1(1),
    LVL2(3),
    NONE(5)
}

fun Sexe.print() : String {
    val res = when(this) {
        Sexe.MASCULIN -> "masculin"
        Sexe.FEMININ -> "féminin"
    }
    return res
}

fun Visage.print() : String {
    val res = when(this) {
        Visage.UNIFORME -> "uniforme"
        Visage.TACHE_ROUSSEUR -> "tacheté"
    }
    return res
}

fun Cheveux.print() : String {
    val res = when(this) {
        Cheveux.MIXTE -> "mixte"
        Cheveux.ANNEE_70 -> "court"
        Cheveux.COUETTES -> "couettes"
        Cheveux.FRANGE -> "frange"
        Cheveux.AU_BOL -> "au bol"
        Cheveux.DECOIFFE_F -> "décoiffée"
        Cheveux.CHEVAL -> "queue de cheval"
        Cheveux.MICKEY -> "mickey"
        Cheveux.TRESSES -> "tresses"
        Cheveux.MI_LONG -> "mi long"
        Cheveux.RAIE -> "raie"
        Cheveux.MEDUSE -> "au bol 2"
        Cheveux.KASPA -> "kaspa"
        Cheveux.CULBUR -> "culbur"
        Cheveux.HIGEM -> "higem"
        Cheveux.ELVIS -> "elvis"
        Cheveux.HERISSON -> "hérisson"
        Cheveux.MANGA -> "manga"
        Cheveux.ARRIERE -> "en arrière"
        Cheveux.BULBE -> "bulbe"
        Cheveux.CRETE -> "iroquois"
        Cheveux.EPI1 -> "épi 1"
        Cheveux.EPI2 -> "épi 2"
        Cheveux.EPI3 -> "épi 3"
        Cheveux.DECOIFFE_H -> "décoiffé"
        Cheveux.MECHE_AVANT -> "mèche en avant"
        Cheveux.STEADY -> "court"
    }
    return res
}

fun Yeux.print() : String {
    val res = when(this) {
        Yeux.COLERE_F -> "en colère"
        Yeux.COLERE_H -> "en colère"
        Yeux.FATIGUE -> "fatigue"
        Yeux.INQUIET -> "inquiète"
        Yeux.MANGA_F -> "manga"
        Yeux.MANGA_H -> "manga"
        Yeux.AMANDE -> "en amande"
        Yeux.GROS -> "gros"
        Yeux.EYELINER -> "eyeliner"
        Yeux.INSOMNIE -> "insomnie"
    }
    return res
}

fun Bouche.print() : String {
    val res = when(this) {
        Bouche.TETINE -> "tétine"
        Bouche.PETITE -> "petite"
        Bouche.MOYENNE -> "moyenne"
        Bouche.GRANDE -> "grande"
        Bouche.MACHOIRE -> "machoire"
        Bouche.SOURIRE -> "sourire"
    }
    return res
}

fun Bonnet.print() : String {
    val res = when(this) {
        Bonnet.LVL0 -> "couvrant"
        Bonnet.LVL1 -> "bouche visible"
        Bonnet.LVL2 -> "normal"
        Bonnet.NONE -> "aucun"
    }
    return res
}

@Composable
fun PrintFeatures(viewModel: AppViewModel, textSize: TextUnit, modifier: Modifier){
    Box(modifier = modifier
    ) {
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            val frutifamily = FontFamily(Font(R.font.pacifico_regular, FontWeight.Normal, FontStyle.Normal))
            val defaultFont = FontFamily.Monospace

            // attributs de la bouille à éditer
            // ================================
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.4f)
                .verticalScroll(viewModel.scrollFeatures)
            ) {
                val bouilleProperties = listOf<String>("Sexe", "Visage", "Cheveux", "Yeux", "Bouche", "Bonnet")
                for(i in 0 until 6) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (viewModel.activeFeature.value == i) {
                                fpWhitePeach
                            } else {
                                fpDarkGreen
                            }
                        )
                        .padding(start = 10.dp, end = 10.dp), onClick = {
                        viewModel.activeFeature.value = i
                    }, colors = ButtonColors(containerColor = fpGreen, contentColor = Color.Black, disabledContentColor = Color.Black, disabledContainerColor = Color.Gray), border = BorderStroke(width = 2.dp, color = Color.Gray)
                    ) {
                        Text(text = bouilleProperties[i], fontSize = textSize, fontFamily = defaultFont)
                    }
                }
            }

            // sélection d'une valeur d'attribut
            // =================================
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(fpWhitePeach)
                .verticalScroll(viewModel.scrollFeatureOptions)
            ) {
                if(viewModel.sexe.value == Sexe.MASCULIN) {
                    PrintFeatureOptions(viewModel, defaultFont)
                }
                else {
                    PrintFeatureOptions(viewModel, defaultFont)
                }
            }
        }
    }
}

@Composable
fun PrintFeatureOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    val activeFeature = viewModel.activeFeature.value
    when(activeFeature) {
        0 -> PrintSexOptions(viewModel, fontFamily)
        1 -> PrintFaceOptions(viewModel, fontFamily)
        2 -> PrintHairOptions(viewModel, fontFamily)
        3 -> PrintEyesOptions(viewModel, fontFamily)
        4 -> PrintMouthOptions(viewModel, fontFamily)
        5 -> PrintSackOptions(viewModel, fontFamily)
    }
}

@Composable
fun PrintSexOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    for(i in 0..1) {
        Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = (viewModel.sexe.value.value == i), onClick = {
                if(viewModel.sexe.value.value != i) {
                    viewModel.sexe.value = Sexe.entries.first { it.value == i }
                }
            })
            Text(text = Sexe.entries.first { it.value == i }.print(), fontFamily = fontFamily)
        }
    }
}

@Composable
fun PrintFaceOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    val bouille = if(viewModel.sexe.value == Sexe.MASCULIN) {
        viewModel.maleFrutibouille
    }
    else {
        viewModel.femaleFrutibouille
    }

    for(i in 0..1) {
        Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = (bouille.visage.value.value == i), onClick = {
                if(bouille.visage.value.value != i) {
                    bouille.visage.value = Visage.entries.first { it.value == i }
                }
            })
            Text(text = Visage.entries.first { it.value == i }.print(), fontFamily = fontFamily)
        }
    }
}

@Composable
fun PrintHairOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    val femme = listOf<Int>(0, 22, 28, 32, 24, 30, 36, 26, 42, 34, 38, 40)
    val homme = listOf<Int>(0, 20, 4, 13, 7, 12, 15, 2, 3, 18, 8, 10, 11, 6, 16, 17)

    val bouille = if(viewModel.sexe.value == Sexe.MASCULIN) {
        viewModel.maleFrutibouille
    }
    else {
        viewModel.femaleFrutibouille
    }

    if(bouille.sexe.value == Sexe.MASCULIN) {
        for(i in homme) {
            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = (bouille.cheveux.value.value == i), onClick = {
                    if(bouille.cheveux.value.value != i) {
                        bouille.cheveux.value = Cheveux.entries.first { it.value == i }
                    }
                })
                Text(text = Cheveux.entries.first { it.value == i }.print(), fontFamily = fontFamily)
            }
        }
    }
    else {
        for(i in femme) {
            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = (bouille.cheveux.value.value == i), onClick = {
                    if(bouille.cheveux.value.value != i) {
                        bouille.cheveux.value = Cheveux.entries.first { it.value == i }
                    }
                })
                Text(text = Cheveux.entries.first { it.value == i }.print(), fontFamily = fontFamily)
            }
        }
    }
}

@Composable
fun PrintEyesOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    val femme = listOf<Int>(11, 14, 15, 12, 2, 0)
    val homme = listOf<Int>(6, 9, 4, 7, 2, 0)

    val bouille = if(viewModel.sexe.value == Sexe.MASCULIN) {
        viewModel.maleFrutibouille
    }
    else {
        viewModel.femaleFrutibouille
    }

    if(bouille.sexe.value == Sexe.MASCULIN) {
        for(i in homme) {
            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = (bouille.yeux.value.value == i), onClick = {
                    if(bouille.yeux.value.value != i) {
                        bouille.yeux.value = Yeux.entries.first { it.value == i }
                    }
                })
                Text(text = Yeux.entries.first { it.value == i }.print(), fontFamily = fontFamily)
            }
        }
    }
    else {
        for(i in femme) {
            Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = (bouille.yeux.value.value == i), onClick = {
                    if(bouille.yeux.value.value != i) {
                        bouille.yeux.value = Yeux.entries.first { it.value == i }
                    }
                })
                Text(text = Yeux.entries.first { it.value == i }.print(), fontFamily = fontFamily)
            }
        }
    }
}

@Composable
fun PrintMouthOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    val indexBouche = listOf<Int>(7, 3, 0, 1, 2, 5)
    val bouille = if(viewModel.sexe.value == Sexe.MASCULIN) {
        viewModel.maleFrutibouille
    }
    else {
        viewModel.femaleFrutibouille
    }
    for(i in indexBouche) {
        Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = (bouille.bouche.value.value == i), onClick = {
                if(bouille.bouche.value.value != i) {
                    bouille.bouche.value = Bouche.entries.first { it.value == i }
                }
            })
            Text(text = Bouche.entries.first { it.value == i }.print(), fontFamily = fontFamily)
        }
    }
}

@Composable
fun PrintSackOptions(viewModel: AppViewModel, fontFamily: FontFamily) {
    val indexBonnet = listOf(0, 1, 3, 5)
    val bouille = if(viewModel.sexe.value == Sexe.MASCULIN) {
        viewModel.maleFrutibouille
    }
    else {
        viewModel.femaleFrutibouille
    }
    for(i in indexBonnet) {
        Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = (bouille.bonnet.value.value == i), onClick = {
                if(bouille.bonnet.value.value != i) {
                    bouille.bonnet.value = Bonnet.entries.first { it.value == i }
                }
            })
            Text(text = Bonnet.entries.first { it.value == i }.print(), fontFamily = fontFamily)
        }
    }
}