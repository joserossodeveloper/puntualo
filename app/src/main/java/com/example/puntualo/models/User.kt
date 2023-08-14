package com.example.puntualo.models

import java.util.*

data class User(
    val email: String,
    val password: String,
    val type: String,
    val birthday: Date,
    val dni: String,
    var name: String,
    var father_last_name: String,
    var mother_last_name: String,
    var id: String
)
