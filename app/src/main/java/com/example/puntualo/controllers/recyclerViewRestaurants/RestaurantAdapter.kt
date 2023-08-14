package com.example.puntualo.controllers.recyclerViewRestaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puntualo.models.Restaurant
import com.example.puntualo.R

// NOTE tomar un listado y convertirlo en un recycler view
class RestaurantAdapter(private val restaurantList: List<Restaurant>): RecyclerView.Adapter<RestaurantViewHolder>(){

    var onItemClick: ((Restaurant) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        // NOTE encargado de tomar los datos y mostrarlo
        val layoutInflater = LayoutInflater.from(parent.context)
        return RestaurantViewHolder(layoutInflater.inflate(R.layout.item_restaurant, parent, false))
    }

    override fun getItemCount(): Int {
        // NOTE indicar cuantos elementos tiene que manejar el recycler view
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        // NOTE pasa la data a cada item
        val item = restaurantList[position]
        // NOTE ver detalle
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(item)
        }
        holder.render(item)
    }
}