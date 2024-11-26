package com.vargas.tarea2.DatasClass

import java.io.Serializable

data class Usuario(
    val nombre: String,
    val apellidos: String,
    val phone: String,
    val email: String,
    val password: String,
    val edad: Int,
    val annio: Int,
): Serializable
