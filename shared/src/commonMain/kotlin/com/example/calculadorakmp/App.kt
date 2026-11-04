package com.example.calculadorakmp

import androidx.compose.runtime.*
import com.example.calculadorakmp.ui.theme.CalculadoraKMP.CalculadoraComposeTheme

@Composable
fun App(
    reproducirSonido: () -> Unit,
    onFinish: () -> Unit = {}
) {
    var modoOscuro by remember { mutableStateOf(false) }

    CalculadoraComposeTheme(
        darkTheme = modoOscuro
    ) {
        Navegacion(
            cambiarTema = {
                modoOscuro = !modoOscuro
            },
            reproducirSonido = { reproducirSonido() },
            onFinish = onFinish
        )
    }
}