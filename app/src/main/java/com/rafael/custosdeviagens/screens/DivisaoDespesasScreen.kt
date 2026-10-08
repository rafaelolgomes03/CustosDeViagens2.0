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
fun TelaDivisaoDespesas(
    passageiros: String,
    todosPagam: Boolean,
    onPassageiros: (String) -> Unit,
    onTodosPagam: (Boolean) -> Unit,
    onCalcular: () -> Unit,
    onBack: () -> Unit,
    navController: NavHostController
) {
    TelaComNavegacao(
        titulo = "Divisão de Despesas",
        navController = navController,
        onBack = onBack,
        conteudo = {
            Text("Defina como as despesas serão divididas.", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(16.dp))
            Campo("Quantidade de passageiros", passageiros, onPassageiros)
            Text("Quem participa do pagamento?", fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = todosPagam, onCheckedChange = { onTodosPagam(true) })
                Text("Todos pagam a mesma quantia")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = !todosPagam, onCheckedChange = { onTodosPagam(false) })
                Text("Motorista não paga")
            }
            Spacer(Modifier.height(18.dp))
            Button(onClick = onCalcular, Modifier.fillMaxWidth().height(55.dp)) {
                Text("Calcular viagem", fontSize = 18.sp)
            }
        },
        selectedRoute = Rotas.DIVISAO
    )
}
