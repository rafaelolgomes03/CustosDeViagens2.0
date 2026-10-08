package com.rafael.custosdeviagens.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rafael.custosdeviagens.components.*
import com.rafael.custosdeviagens.model.*
import com.rafael.custosdeviagens.navigation.Rotas

@Composable
fun TelaBoasVindas(onIniciar: () -> Unit) {
    Scaffold { padding ->
        Box(
            Modifier.fillMaxSize().padding(padding).background(Color(0xFFEAF4FF)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("🚗", fontSize = 70.sp)
                Spacer(Modifier.height(16.dp))
                Text(
                    "Custos de Viagens",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1565C0),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "Calcule combustível, pedágios, gastos extras e divisão das despesas.",
                    fontSize = 17.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(32.dp))
                Button(
                    onClick = onIniciar,
                    Modifier.fillMaxWidth().height(55.dp)
                ) { Text("Iniciar Nova Viagem", fontSize = 18.sp) }
            }
        }
    }
}
