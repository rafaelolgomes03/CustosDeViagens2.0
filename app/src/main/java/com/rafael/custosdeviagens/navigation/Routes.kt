package com.rafael.custosdeviagens.navigation

object Rotas {
    const val BOAS_VINDAS = "boas_vindas"
    const val PERFIL = "perfil"
    const val ROTA = "rota"
    const val COMBUSTIVEL = "combustivel"
    const val EXTRAS = "extras"
    const val DIVISAO = "divisao"
    const val RESUMO = "resumo"
    const val VEICULOS = "veiculos"
    const val VEICULO_DETALHE = "veiculo_detalhe/{id}"
    const val GASTOS = "gastos"
    const val GASTO_DETALHE = "gasto_detalhe/{id}"
    fun veiculoDetalhe(id: Int) = "veiculo_detalhe/$id"
    fun gastoDetalhe(id: Int) = "gasto_detalhe/$id"
}
