package com.example.calculadorakmp

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private lateinit var soundPool: SoundPool
    private val sonidosCargadosASoudPool = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        configurarSoundPool()
        cargarSonidosASoundPool()

        setContent {
            App(
                reproducirSonido = { reproducirSonido() },
                onFinish = { finish() }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
        sonidosCargadosASoudPool.clear()
    }

    private fun configurarSoundPool(){
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    private fun cargarSonidosASoundPool(){

        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0){
                sonidosCargadosASoudPool.add(sampleId)
            }
        }

        for (sonido in listaDeSonidosSillies){
            soundPool.load(this, sonido, 1)
        }
    }

    private fun reproducirSonido(){
        if (sonidosCargadosASoudPool.isEmpty()) return
        val sonidoElegido = sonidosCargadosASoudPool.random()
        soundPool.play(
            sonidoElegido,
            1f,
            1f,
            1,
            0,
            1f
        )
    }

    private val listaDeSonidosSillies = listOf(
        R.raw.sonido_silly_1,
        R.raw.sonido_silly_2,
        R.raw.sonido_silly_3,
        R.raw.sonido_silly_4,
        R.raw.sonido_silly_5,
        R.raw.sonido_silly_6,
        R.raw.sonido_silly_7,
        R.raw.sonido_silly_8,
        R.raw.sonido_silly_9,
        R.raw.sonido_silly_10,
        R.raw.sonido_silly_11,
        R.raw.sonido_silly_12,
        R.raw.sonido_silly_13,
        R.raw.sonido_silly_14,
        R.raw.sonido_silly_15,
        R.raw.sonido_silly_16
    )
}