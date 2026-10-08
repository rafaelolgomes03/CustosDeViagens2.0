package com.rafael.custosdeviagens

    import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rafael.custosdeviagens.model.*
import com.rafael.custosdeviagens.navigation.Rotas
import com.rafael.custosdeviagens.components.toDoubleBR
import com.rafael.custosdeviagens.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CustosDeViagensApp() }
    }
}

@Composable
fun CustosDeViagensApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var distancia by rememberSaveable { mutableStateOf("") }
    var consumo by rememberSaveable { mutableStateOf("") }
    var precoCombustivel by rememberSaveable { mutableStateOf("") }
    var pedagio by rememberSaveable { mutableStateOf("") }
    var tipoVeiculo by rememberSaveable { mutableStateOf("Carro") }
    var tipoCombustivel by rememberSaveable { mutableStateOf("Gasolina") }
    var estacionamento by rememberSaveable { mutableStateOf("") }
    var alimentacao by rememberSaveable { mutableStateOf("") }
    var imprevistos by rememberSaveable { mutableStateOf("") }
    var passageiros by rememberSaveable { mutableStateOf("1") }
    var todosPagam by rememberSaveable { mutableStateOf(true) }
    var litrosGastos by rememberSaveable { mutableStateOf(0.0) }
    var custoCombustivel by rememberSaveable { mutableStateOf(0.0) }
    var custoExtras by rememberSaveable { mutableStateOf(0.0) }
    var custoTotal by rememberSaveable { mutableStateOf(0.0) }
    var custoPorPessoa by rememberSaveable { mutableStateOf(0.0) }

    val veiculos = remember { mutableStateListOf(Vehicle(1,"Carro","Gasolina"), Vehicle(2,"Moto","Etanol")) }
    val gastos = remember { mutableStateListOf(Expense(1,"Estacionamento",20.0), Expense(2,"Alimentação na estrada",35.0)) }

    fun calcular() {
        val d=distancia.toDoubleBR(); val c=consumo.toDoubleBR(); val p=precoCombustivel.toDoubleBR(); val ped=pedagio.toDoubleBR()
        val est=estacionamento.toDoubleBR()?:0.0; val ali=alimentacao.toDoubleBR()?:0.0; val imp=imprevistos.toDoubleBR()?:0.0; val n=passageiros.toIntOrNull()
        if(d==null||c==null||p==null||ped==null||n==null||d<=0||c<=0||p<0||ped<0||est<0||ali<0||imp<0||n<1){ Toast.makeText(context,"Preencha os campos corretamente.",Toast.LENGTH_SHORT).show(); return }
        litrosGastos=d/c; custoCombustivel=litrosGastos*p; custoExtras=est+ali+imp; custoTotal=custoCombustivel+ped+custoExtras
        val divisor=if(todosPagam)n+1 else n; custoPorPessoa=custoTotal/divisor.coerceAtLeast(1); navController.navigate(Rotas.RESUMO)
    }

    NavHost(navController=navController,startDestination=Rotas.BOAS_VINDAS){
        composable(Rotas.BOAS_VINDAS){ TelaBoasVindas{navController.navigate(Rotas.PERFIL)} }
        composable(Rotas.PERFIL) {
            TelaPerfilVeiculo(
                tipoVeiculo = tipoVeiculo,
                tipoCombustivel = tipoCombustivel,
                onTipoVeiculo = { tipoVeiculo = it },
                onTipoCombustivel = { tipoCombustivel = it },
                onContinuar = { navController.navigate(Rotas.ROTA) },
                onGerenciar = { navController.navigate(Rotas.VEICULOS) },
                onBack = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(Rotas.ROTA){ TelaDadosRota(distancia,consumo,{distancia=it},{consumo=it},{navController.navigate(Rotas.COMBUSTIVEL)},{navController.popBackStack()},navController) }
        composable(Rotas.COMBUSTIVEL){ TelaCombustivelPedagio(precoCombustivel,pedagio,{precoCombustivel=it},{pedagio=it},{navController.navigate(Rotas.EXTRAS)},{navController.popBackStack()},navController) }
        composable(Rotas.EXTRAS){ TelaGastosAdicionais(estacionamento,alimentacao,imprevistos,{estacionamento=it},{alimentacao=it},{imprevistos=it},{navController.navigate(Rotas.DIVISAO)},{navController.navigate(Rotas.GASTOS)},{navController.popBackStack()},navController) }
        composable(Rotas.DIVISAO){ TelaDivisaoDespesas(passageiros,todosPagam,{passageiros=it},{todosPagam=it},{calcular()},{navController.popBackStack()},navController) }
        composable(Rotas.RESUMO){ TelaResumo(tipoVeiculo,tipoCombustivel,distancia,consumo,pedagio,passageiros,todosPagam,litrosGastos,custoCombustivel,custoExtras,custoTotal,custoPorPessoa,{Toast.makeText(context,"Viagem salva na memória do aplicativo.",Toast.LENGTH_SHORT).show()},{navController.navigate(Rotas.PERFIL)},{navController.popBackStack()},navController) }
        composable(Rotas.VEICULOS){ TelaListaVeiculos(veiculos,{tipo,comb -> veiculos.add(Vehicle((veiculos.maxOfOrNull{it.id}?:0)+1,tipo,comb))},{veiculos.remove(it)},{navController.navigate(Rotas.veiculoDetalhe(it.id))},{navController.popBackStack()}) }
        composable(Rotas.VEICULO_DETALHE){e-> TelaDetalheVeiculo(veiculos.firstOrNull{it.id==e.arguments?.getString("id")?.toIntOrNull()},{navController.popBackStack()})}
        composable(Rotas.GASTOS){ TelaListaGastos(gastos,{desc,v->gastos.add(Expense((gastos.maxOfOrNull{it.id}?:0)+1,desc,v))},{gastos.remove(it)},{navController.navigate(Rotas.gastoDetalhe(it.id))},{navController.popBackStack()}) }
        composable(Rotas.GASTO_DETALHE){e-> TelaDetalheGasto(gastos.firstOrNull{it.id==e.arguments?.getString("id")?.toIntOrNull()},{navController.popBackStack()})}
    }
}
