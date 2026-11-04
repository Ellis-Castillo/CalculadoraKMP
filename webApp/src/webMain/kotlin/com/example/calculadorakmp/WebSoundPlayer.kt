package com.example.calculadorakmp

import calculadorakmp.shared.generated.resources.Res
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLAudioElement

class WebSoundPlayer {

    private val sonidos = mutableListOf<HTMLAudioElement>()

    suspend fun cargarSonidos() {

        for (numero in 1..16) {
            try {
                val bytes = Res.readBytes(
                    "files/sonido_silly_$numero.wav"
                )

                val binario = buildString {
                    bytes.forEach { byte ->
                        append((byte.toInt() and 0xFF).toChar())
                    }
                }

                val base64 = window.btoa(binario)
                val audio = document.createElement("audio") as HTMLAudioElement

                audio.src = "data:audio/wav;base64,$base64"
                audio.preload = "auto"

                sonidos.add(audio)

            } catch (e: Exception) {
                println("Error cargando sonido $numero: ${e.message}")
            }
        }
    }

    fun reproducirSonido() {
        if (sonidos.isEmpty()) return

        val sonido = sonidos.random()

        sonido.pause()
        sonido.currentTime = 0.0
        sonido.play()
    }
}