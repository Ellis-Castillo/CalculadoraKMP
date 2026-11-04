package com.example.calculadorakmp

import calculadorakmp.shared.generated.resources.Res
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import java.io.ByteArrayInputStream

class DesktopSoundPlayer {

    private val sonidosCargados = mutableListOf<Clip>()

    suspend fun cargarSonidos() {

        val listaDeSonidos = listOf(
            "files/sonido_silly_1.wav",
            "files/sonido_silly_2.wav",
            "files/sonido_silly_3.wav",
            "files/sonido_silly_4.wav",
            "files/sonido_silly_5.wav",
            "files/sonido_silly_6.wav",
            "files/sonido_silly_7.wav",
            "files/sonido_silly_8.wav",
            "files/sonido_silly_9.wav",
            "files/sonido_silly_10.wav",
            "files/sonido_silly_11.wav",
            "files/sonido_silly_12.wav",
            "files/sonido_silly_13.wav",
            "files/sonido_silly_14.wav",
            "files/sonido_silly_15.wav",
            "files/sonido_silly_16.wav"
        )

        for (sonido in listaDeSonidos) {
            try {
                val bytes = Res.readBytes(sonido)

                val audioInputStream = AudioSystem.getAudioInputStream(
                    ByteArrayInputStream(bytes)
                )

                val clip = AudioSystem.getClip()
                clip.open(audioInputStream)

                sonidosCargados.add(clip)

                audioInputStream.close()

            } catch (e: Exception) {
                println("Error cargando $sonido: ${e.message}")
            }
        }
    }

    fun reproducirSonido() {
        if (sonidosCargados.isEmpty()) return

        val sonidoElegido = sonidosCargados.random()

        sonidoElegido.stop()
        sonidoElegido.framePosition = 0
        sonidoElegido.start()
    }

    fun liberarRecursos() {
        sonidosCargados.forEach {
            it.stop()
            it.close()
        }

        sonidosCargados.clear()
    }
}