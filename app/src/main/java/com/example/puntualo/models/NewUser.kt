package com.example.puntualo.models

import java.util.Date

data class NewUser (
    val birthday: Date,
    val dni: String,
    val email: String,
    var father_last_name: String,
    var mother_last_name: String,
    var name: String,
    val password: String,
    val type: String,
)