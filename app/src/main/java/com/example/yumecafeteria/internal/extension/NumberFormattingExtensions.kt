package com.example.yumecafeteria.internal.extension

import java.text.NumberFormat
import java.util.Locale

fun Double.formatAsCurrency(): String {
    val localeBrazil = Locale("pt", "BR")
    val numberFormat = NumberFormat.getCurrencyInstance(localeBrazil)
    return numberFormat.format(this)
}

fun Int.formatAsCustomUnit(): String {
    return when (this) {
        0, 1 -> "$this unit."
        else -> "$this units."
    }
}