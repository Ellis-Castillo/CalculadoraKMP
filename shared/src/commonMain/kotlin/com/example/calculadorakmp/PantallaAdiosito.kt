package com.example.calculadorakmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import calculadorakmp.shared.generated.resources.Res
import calculadorakmp.shared.generated.resources.logo_app
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun PantallaAdiosito(
    onFinish: () -> Unit = {}
){
    LaunchedEffect(Unit) {
        delay(2000.milliseconds)
        onFinish()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .fillMaxSize()
                    .weight(3f)
                    .align(Alignment.CenterHorizontally)
            ){
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "¡Hasta la próxima!",
                    fontSize = 52.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .offset(y = 12.dp)
                )
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize()
                    .weight(4f)
            ){}
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                //.offset(y = 100.dp)
                .padding(horizontal = 60.dp, vertical = 44.dp)
        ) {
            Image(
                contentDescription = "",
                painter = painterResource(Res.drawable.logo_app),
                modifier = Modifier
                    .size(140.dp)
            )
        }
    }
}