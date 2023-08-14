package com.example.puntualo.controllers.recyclerViewRestaurants

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puntualo.models.Restaurant
import com.example.puntualo.R

class RestaurantViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val imgRestaurantPhoto = view.findViewById<ImageView>(R.id.imgRestaurantPhoto)
    val textRestaurantName = view.findViewById<TextView>(R.id.textRestaurantName)
    val textRestaurantSpecialty = view.findViewById<TextView>(R.id.textRestaurantSpecialty)

    fun render(restaurant: Restaurant) {
        textRestaurantName.text = restaurant.name
        textRestaurantSpecialty.text = restaurant.specialty
        Glide.with(imgRestaurantPhoto.context).load(restaurant.photo).into(imgRestaurantPhoto)
    }
}