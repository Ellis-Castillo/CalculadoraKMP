package com.example.calculadorakmp


import androidx.compose.runtime.*
import androidx.navigation.compose.*
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun Navegacion(
    cambiarTema: () -> Unit,
    reproducirSonido: () -> Unit,
    onFinish: () -> Unit
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.Inicio.ruta
    ){

        //ENTRADA
        composable(Rutas.Inicio.ruta){
            LaunchedEffect(Unit) {
                delay(2000.milliseconds)

                navController.navigate(Rutas.Principal.ruta){
                    popUpTo(Rutas.Inicio.ruta){
                        inclusive = true
                    }
                }
            }

            PantallaInicio(
                continuar = {
                    navController.navigate(Rutas.Principal.ruta){
                        popUpTo(Rutas.Inicio.ruta){
                            inclusive = true
                        }
                    }
                }
            )
        }

        //PRINCIPAL
        composable(Rutas.Principal.ruta){
            PantallaPrincipal(
                cambiarTema = cambiarTema,
                reproducirSonido = reproducirSonido,
                salir = {
                    navController.navigate(Rutas.Adiosito.ruta)
                }
            )
        }

        //SALIDA
        composable(Rutas.Adiosito.ruta){
            PantallaAdiosito(
                onFinish = onFinish
            )
        }
    }
}