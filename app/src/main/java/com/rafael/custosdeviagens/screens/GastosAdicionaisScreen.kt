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
fun TelaGastosAdicionais(
    estacionamento: String,
    alimentacao: String,
    imprevistos: String,
    onEstacionamento: (String) -> Unit,
    onAlimentacao: (String) -> Unit,
    onImprevistos: (String) -> Unit,
    onContinuar: () -> Unit,
    onGerenciar: () -> Unit,
    onBack: () -> Unit,
    navController: NavHostController
) {
    TelaComNavegacao(
        titulo = "Gastos Adicionais",
        navController = navController,
        onBack = onBack,
        conteudo = {
            Text("Inclua outros custos previstos para a viagem.", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Campo("Estacionamento (R$)", estacionamento, onEstacionamento)
            Campo("Alimentação na estrada (R$)", alimentacao, onAlimentacao)
            Campo("Imprevistos / seguro (R$)", imprevistos, onImprevistos)
            Spacer(Modifier.height(10.dp))
            OutlinedButton(onClick = onGerenciar, Modifier.fillMaxWidth()) {
                Text("Gerenciar gastos adicionais")
            }
            Spacer(Modifier.height(10.dp))
            Button(onClick = onContinuar, Modifier.fillMaxWidth().height(52.dp)) {
                Text("Próximo")
            }
        },
        selectedRoute = Rotas.EXTRAS
    )
}
