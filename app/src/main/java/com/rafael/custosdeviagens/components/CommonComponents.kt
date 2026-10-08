package com.rafael.custosdeviagens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rafael.custosdeviagens.navigation.Rotas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaComNavegacao(
    titulo: String,
    navController: NavHostController,
    onBack: () -> Unit,
    selectedRoute: String,
    conteudo: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(titulo, fontWeight = FontWeight.Bold) },
                navigationIcon = { TextButton(onClick = onBack) { Text("← Voltar") } }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = selectedRoute == Rotas.PERFIL, onClick = { navController.navigate(Rotas.PERFIL) }, icon = { Text("🚗") }, label = { Text("Veículo") })
                NavigationBarItem(selected = selectedRoute in listOf(Rotas.ROTA, Rotas.COMBUSTIVEL, Rotas.EXTRAS, Rotas.DIVISAO), onClick = { navController.navigate(Rotas.ROTA) }, icon = { Text("🧭") }, label = { Text("Viagem") })
                NavigationBarItem(selected = selectedRoute == Rotas.RESUMO, onClick = { navController.navigate(Rotas.RESUMO) }, icon = { Text("📊") }, label = { Text("Resumo") })
            }
        }
    ) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(20.dp), content = conteudo)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Seletor(label: String, valor: String, opcoes: List<String>, onSelecionar: (String) -> Unit) {
    var aberto by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = aberto, onExpandedChange = { aberto = !aberto }) {
        OutlinedTextField(value = valor, onValueChange = {}, readOnly = true, label = { Text(label) }, modifier = Modifier.fillMaxWidth().menuAnchor())
        ExposedDropdownMenu(expanded = aberto, onDismissRequest = { aberto = false }) {
            opcoes.forEach { opcao -> DropdownMenuItem(text = { Text(opcao) }, onClick = { onSelecionar(opcao); aberto = false }) }
        }
    }
}

@Composable
fun Campo(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(label) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
    Spacer(Modifier.height(10.dp))
}

@Composable
fun ItemResumo(titulo: String, valor: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(titulo, fontSize = 16.sp)
        Text(valor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
