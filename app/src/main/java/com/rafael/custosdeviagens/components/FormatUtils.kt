package com.rafael.custosdeviagens.components

import java.util.Locale

fun String.toDoubleBR(): Double? = replace(",", ".").toDoubleOrNull()
fun formatar(valor: Double): String = String.format(Locale("pt", "BR"), "%.2f", valor)
