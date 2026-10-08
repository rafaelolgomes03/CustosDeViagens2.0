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
fun TelaResumo(
    tipoVeiculo: String,
    tipoCombustivel: String,
    distancia: String,
    consumo: String,
    pedagio: String,
    passageiros: String,
    todosPagam: Boolean,
    litrosGastos: Double,
    custoCombustivel: Double,
    custoExtras: Double,
    custoTotal: Double,
    custoPorPessoa: Double,
    onSalvar: () -> Unit,
    onNovaViagem: () -> Unit,
    onBack: () -> Unit,
    navController: NavHostController
) {
    TelaComNavegacao(
        titulo = "Resumo / Resultado Final",
        navController = navController,
        onBack = onBack,
        selectedRoute = Rotas.RESUMO,
        conteudo = {
            Text("Resultado da viagem", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
            Spacer(Modifier.height(12.dp))
            Card(
                Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(18.dp)) {
                    ItemResumo("Veículo", "$tipoVeiculo • $tipoCombustivel")
                    ItemResumo("Distância", "$distancia km")
                    ItemResumo("Consumo médio", "$consumo km/L")
                    ItemResumo("Litros consumidos", "${formatar(litrosGastos)} L")
                    ItemResumo("Combustível", "R$ ${formatar(custoCombustivel)}")
                    ItemResumo("Pedágios", "R$ ${formatar(pedagio.toDoubleBR() ?: 0.0)}")
                    ItemResumo("Gastos adicionais", "R$ ${formatar(custoExtras)}")
                    ItemResumo("Participantes", if (todosPagam) "${passageiros.toIntOrNull()?.plus(1) ?: passageiros} (todos pagam)" else "$passageiros (motorista não paga)")
                    HorizontalDivider(Modifier.padding(vertical = 10.dp))
                    Text("CUSTO TOTAL", fontWeight = FontWeight.Bold)
                    Text("R$ ${formatar(custoTotal)}", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
                    Spacer(Modifier.height(8.dp))
                    Text("Custo por pessoa", fontWeight = FontWeight.Bold)
                    Text("R$ ${formatar(custoPorPessoa)}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = onSalvar, Modifier.fillMaxWidth()) {
                Text("Salvar Viagem")
            }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onNovaViagem, Modifier.fillMaxWidth()) {
                Text("Nova viagem")
            }
        }
    )
}
