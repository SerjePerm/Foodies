package com.example.foodies.utils

fun realToString(value: Int): String {
    val integer = value / 100
    val fractional = value % 100
    return if (fractional > 0) "$integer,$fractional ₽" else "$integer ₽"
}