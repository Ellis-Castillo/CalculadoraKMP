package com.example.calculadorakmp

import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CalculadoraKMP",
    ) {
        val soundPlayer = remember {
            DesktopSoundPlayer()
        }

        LaunchedEffect(Unit) {
            soundPlayer.cargarSonidos()
        }

        DisposableEffect(Unit) {
            onDispose {
                soundPlayer.liberarRecursos()
            }
        }

        App(
            reproducirSonido = {
                soundPlayer.reproducirSonido()
            },
            onFinish = {
                exitApplication()
            }
        )
    }
}