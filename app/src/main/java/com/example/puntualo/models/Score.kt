package com.example.puntualo.models

data class Score(
    val comment: String,
    val creation_date: String,
    val id_restaurant: String,
    val score_infrastructure: Int,
    val score_product: Int,
    val score_security: Int,
    val score_service: Int,
    val update_date: String,
    val id_user: String,
    val user_name: String,
    val id: String
)
