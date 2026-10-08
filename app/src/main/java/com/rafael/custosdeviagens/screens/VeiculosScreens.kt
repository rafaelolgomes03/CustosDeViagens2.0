@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
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
fun TelaListaVeiculos(
    veiculos: List<Vehicle>,
    onAdicionar: (String, String) -> Unit,
    onRemover: (Vehicle) -> Unit,
    onDetalhe: (Vehicle) -> Unit,
    onBack: () -> Unit
) {
    var tipo by rememberSaveable { mutableStateOf("Carro") }
    var combustivel by rememberSaveable { mutableStateOf("Gasolina") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Veículos") },
                navigationIcon = { TextButton(onClick = onBack) { Text("← Voltar") } }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Adicionar veículo", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Seletor("Tipo", tipo, listOf("Carro", "Moto", "SUV"), { tipo = it })
            Spacer(Modifier.height(8.dp))
            Seletor("Combustível", combustivel, listOf("Gasolina", "Etanol", "Diesel"), { combustivel = it })
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = { onAdicionar(tipo, combustivel) },
                Modifier.fillMaxWidth()
            ) { Text("Adicionar") }

            Spacer(Modifier.height(16.dp))
            Text("Veículos cadastrados", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(veiculos, key = { it.id }) { veiculo ->
                    Card(
                        Modifier.fillMaxWidth().clickable { onDetalhe(veiculo) }
                    ) {
                        Row(
                            Modifier.fillMaxWidth().padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(veiculo.type, fontWeight = FontWeight.Bold)
                                Text(veiculo.fuel)
                            }
                            TextButton(onClick = { onRemover(veiculo) }) {
                                Text("Remover")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TelaDetalheVeiculo(
    veiculo: Vehicle?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhe do Veículo") },
                navigationIcon = { TextButton(onClick = onBack) { Text("← Voltar") } }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
            if (veiculo == null) {
                Text("Veículo não encontrado.")
            } else {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text("Tipo: ${veiculo.type}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("Combustível: ${veiculo.fuel}")
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Uso no cálculo: o tipo de combustível define o combustível selecionado para a viagem.",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
