package com.example.calculadorakmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import calculadorakmp.shared.generated.resources.Res
import calculadorakmp.shared.generated.resources.logo_app

@Composable
fun PantallaInicio(
    continuar: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .fillMaxSize()
                    .weight(4f)
            ) {}

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize()
                    .weight(3f)
            ) {}
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 100.dp)
        ) {
            Image(
                contentDescription = "Logo de la aplicación",
                painter = painterResource(Res.drawable.logo_app),
                modifier = Modifier.size(280.dp)
            )
        }
    }
}