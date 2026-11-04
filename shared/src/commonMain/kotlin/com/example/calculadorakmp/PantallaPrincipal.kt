package com.example.calculadorakmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun PantallaPrincipal(
    cambiarTema: () -> Unit,
    reproducirSonido: () -> Unit,
    salir: () -> Unit
){
    //var historial by rememberSaveable { mutableStateOf("") }
    var operacionMostrada by rememberSaveable { mutableStateOf("0") }
    var mostrarInformacion by remember {mutableStateOf(false)}
    //var operandoPrimero by remember { mutableDoubleStateOf(0.0) }
    //var operandoSegundo by remember { mutableDoubleStateOf(0.0) }
    //var resultado by remember { mutableDoubleStateOf(0.0) }
    var iniciarSalida by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            SpacerPersonalizadoYBonico(
                "Calc.ulus por Ellis",
                modifier = Modifier
                    .weight(1f)
            )
            ResultadoHistorial(
                operacionMostrada = operacionMostrada,
                modifier = Modifier
                    .weight(3f)
            )
            Teclado(
                operacionMostrada = operacionMostrada,
                cambioOperacion = {operacionMostrada = it},
                cambiarTema = cambiarTema,
                mostrarInformacion = {mostrarInformacion = true},
                reproducirSilly = reproducirSonido,
                salir = {
                    if (!iniciarSalida) {
                        iniciarSalida = true
                    }
                },
                modifier = Modifier
                    .weight(6f)
            )
            SpacerPersonalizadoYBonico(
                "",
                modifier = Modifier
                    .weight(0.25f)
            )
        }
        MensajeInformacion(
            mensaje = """
                Esta app se autodestruirá
                (se cerrará)
                en 5s
            """.trimIndent(),
            visible = mostrarInformacion,
            onDismiss = {
                mostrarInformacion = false
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }

    LaunchedEffect(iniciarSalida) {
        if (iniciarSalida) {
            delay(3000.milliseconds)
            salir()
        }
    }

}

@Composable
fun ResultadoHistorial(
    operacionMostrada: String,
    //historial: String,
    modifier: Modifier = Modifier
){
    //Text(historial)
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .fillMaxSize()
    ) {
        Text(
            text = operacionMostrada,
            fontSize = 64.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            overflow = TextOverflow.StartEllipsis,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(y = 28.dp),
        )
    }
}

@Composable
fun Teclado(
    operacionMostrada: String,
    cambioOperacion: (String) -> Unit,
    modifier: Modifier = Modifier,
    cambiarTema: () -> Unit,
    mostrarInformacion: () -> Unit,
    reproducirSilly: () -> Unit,
    salir: () -> Unit
){
    val teclasSinIgual = listOf(
        listOf("i","T","←","%"),
        listOf("7","8","9","/"),
        listOf("4","5","6","x"),
        listOf("1","2","3","-"),
        listOf(",","0","=","+")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(4.dp)
    ) {
        for (fila in teclasSinIgual){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ){
                for (tecla in fila){
                    Boton(
                        simbolo = tecla,
                        onClick = {

                            reproducirSilly()

                            when(tecla){
                                "i" -> {
                                    mostrarInformacion()
                                    salir()
                                }
                                "T" ->
                                    cambiarTema()
                                "←" ->
                                    cambioOperacion(operacionMostrada.dropLast(1))
                                "," ->
                                    if (!operacionMostrada.contains(","))
                                    {cambioOperacion(operacionMostrada + tecla)}
                                    else
                                    {cambioOperacion(operacionMostrada)}
                                "=" ->
                                    cambioOperacion("")
                                else ->
                                    cambioOperacion(operacionMostrada + tecla)
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}

/*
@Composable
fun Calculo(){}

@Composable
fun Resultado(){}

@Composable
fun Historial(){}
 */

@Composable
//Función no de cosecha propia xd sacada de reddit y stack overflow (dará problemas en futuro?)
fun MensajeInformacion(
    mensaje: String,
    visible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(visible) {
        if (visible) {
            delay(2000.milliseconds)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Text(
                text = mensaje,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Boton(
    simbolo: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxSize()
            .padding(2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Text(
            text = simbolo,
            fontSize = 36.sp
        )
    }
}

@Composable
fun SpacerPersonalizadoYBonico(
    texto: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = texto,
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = 24.sp
        )
    }
}