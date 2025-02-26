package com.frutiparc.frutibouilleur

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.frutiparc.frutibouilleur.ui.theme.fpDarkGreen
import com.frutiparc.frutibouilleur.ui.theme.fpGreen
import com.frutiparc.frutibouilleur.ui.theme.fpYellow

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AppViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createShortcut()
        val orientation = resources.configuration.orientation
        setContent {
            Scaffold (
                modifier = Modifier.fillMaxSize(),
                containerColor = fpGreen,
                topBar = { FbTopBar() }
            ) {
                MainContent(
                    orientation = orientation,
                    viewModel = viewModel)
            }
        }
    }

    private fun createShortcut() {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val shortcutCreated = sharedPreferences.getBoolean("shortcutCreated", false)

        if(!shortcutCreated) {
            val shortcutIntent = Intent(applicationContext, MainActivity::class.java).apply {
                action = Intent.ACTION_MAIN
            }

            val shortcut = ShortcutInfoCompat.Builder(this, "frutibouilleur")
                .setShortLabel("Frutibouilleur")
                .setLongLabel("Viens créer ta frutibouille !")
                .setIcon(IconCompat.createWithResource(this, R.drawable.icon_app))
                .setIntent(shortcutIntent)
                .build()

            ShortcutManagerCompat.requestPinShortcut(this, shortcut, null)

            val editor = sharedPreferences.edit()
            editor.putBoolean("shortcutCreated", true)
            editor.apply()
        }
    }
}

@Composable
fun FbTopBar(modifier : Modifier = Modifier
    .fillMaxWidth()
    .height(50.dp)
    .background(fpDarkGreen)
) {
    val frutifamily = FontFamily(Font(R.font.pacifico_regular, FontWeight.Normal, FontStyle.Normal))
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = "Frutibouilleur", fontWeight = FontWeight.Bold, fontSize = 25.sp, fontFamily = frutifamily, color = fpYellow)
    }
}

@Composable
fun MainContent(
    orientation: Int,
    viewModel: AppViewModel) {
    if(orientation == Configuration.ORIENTATION_PORTRAIT) {
        val constraints = createPortraitConstraints()
        DrawPortrait(
            constraints = constraints,
            viewModel = viewModel
        )
    }
    else {
        val constraints = createLandscapeConstraints()
        DrawLandscape(
            constraints = constraints,
            viewModel = viewModel
        )
    }
}

fun createPortraitConstraints(): ConstraintSet {
    val constraints = ConstraintSet {
        val features = createRefFor("features")
        val bouille = createRefFor("bouille")
        val color = createRefFor("color")

        val guideLine1_V = createGuidelineFromTop(0.5f)
        val guideLine2_V = createGuidelineFromTop(0.60f)
        val guideLine3 = createGuidelineFromTop(50.dp)

        constrain(features) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(guideLine2_V)
            bottom.linkTo(parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(bouille) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(guideLine3)
            bottom.linkTo(guideLine1_V)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(color) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(guideLine1_V)
            bottom.linkTo(guideLine2_V)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
    return constraints
}

fun createLandscapeConstraints(): ConstraintSet {
    val constraints = ConstraintSet {
        val features = createRefFor("features")
        val bouille = createRefFor("bouille")
        val color = createRefFor("color")

        val guideLine1_H = createGuidelineFromStart(0.5f)
        val guideLine2_H = createGuidelineFromStart(0.6f)
        val guideLine3 = createGuidelineFromTop(50.dp)

        constrain(features) {
            start.linkTo(parent.start)
            end.linkTo(guideLine1_H)
            top.linkTo(guideLine3)
            bottom.linkTo(parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(bouille) {
            start.linkTo(guideLine2_H)
            end.linkTo(parent.end)
            top.linkTo(guideLine3)
            bottom.linkTo(parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }

        constrain(color) {
            start.linkTo(guideLine1_H)
            end.linkTo(guideLine2_H)
            top.linkTo(guideLine3)
            bottom.linkTo(parent.bottom)

            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
    return constraints
}