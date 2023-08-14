package com.example.puntualo.scripts

import com.example.puntualo.mocks.RestaurantMock
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoadData {
    private val db = Firebase.firestore
/*
    fun loadRestaurants() {
        // limpiar coleccion de resturantes
        val restaurants = db.collection("restaurants")
        for (item in RestaurantMock.restaurantList) {
            restaurants.add(item)
        }
    }*/
}