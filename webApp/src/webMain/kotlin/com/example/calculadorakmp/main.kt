package com.example.calculadorakmp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val soundPlayer = WebSoundPlayer()

    ComposeViewport(
        viewportContainerId = "ComposeTarget"
    ) {
        LaunchedEffect(Unit) {
            soundPlayer.cargarSonidos()
        }

        App(
            reproducirSonido = { soundPlayer.reproducirSonido() },
            onFinish = {}
        )
    }
}