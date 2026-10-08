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
fun TelaListaGastos(
    gastos: List<Expense>,
    onAdicionar: (String, Double) -> Unit,
    onRemover: (Expense) -> Unit,
    onDetalhe: (Expense) -> Unit,
    onBack: () -> Unit
) {
    var descricao by rememberSaveable { mutableStateOf("") }
    var valor by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Gastos") },
                navigationIcon = { TextButton(onClick = onBack) { Text("← Voltar") } }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Text("Adicionar gasto", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))
            Campo("Descrição", descricao, { descricao = it })
            Campo("Valor (R$)", valor, { valor = it })

            Button(
                onClick = {
                    val v = valor.toDoubleBR()
                    if (descricao.isNotBlank() && v != null && v >= 0) {
                        onAdicionar(descricao, v)
                        descricao = ""
                        valor = ""
                    }
                },
                Modifier.fillMaxWidth()
            ) { Text("Adicionar gasto") }

            Spacer(Modifier.height(16.dp))
            Text("Gastos cadastrados", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(8.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(gastos, key = { it.id }) { gasto ->
                    Card(
                        Modifier.fillMaxWidth().clickable { onDetalhe(gasto) }
                    ) {
                        Row(
                            Modifier.fillMaxWidth().padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(gasto.description, fontWeight = FontWeight.Bold)
                                Text("R$ ${formatar(gasto.value)}")
                            }
                            TextButton(onClick = { onRemover(gasto) }) {
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
fun TelaDetalheGasto(
    gasto: Expense?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhe do Gasto") },
                navigationIcon = { TextButton(onClick = onBack) { Text("← Voltar") } }
            )
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp)) {
            if (gasto == null) {
                Text("Gasto não encontrado.")
            } else {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp)) {
                        Text(gasto.description, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text("Valor: R$ ${formatar(gasto.value)}")
                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Impacto no orçamento: este valor é somado aos demais gastos adicionais da viagem.",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
