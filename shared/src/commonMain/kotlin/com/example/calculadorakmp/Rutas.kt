package com.example.calculadorakmp

sealed class Rutas(val ruta: String){
    object Inicio: Rutas("inicio")
    object Principal: Rutas("principal")
    object Adiosito: Rutas("adiosito")
}