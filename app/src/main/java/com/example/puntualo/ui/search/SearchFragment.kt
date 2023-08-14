package com.example.puntualo.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puntualo.R
import com.example.puntualo.RestaurantDetail
import com.example.puntualo.controllers.recyclerViewRestaurants.RestaurantAdapter
import com.example.puntualo.databinding.FragmentSearchBinding
import com.example.puntualo.models.Restaurant
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val db = Firebase.firestore

    private lateinit var recyclerView: RecyclerView
    private var restaurantList = ArrayList<Restaurant>()
    private lateinit var myAdapter: RestaurantAdapter

    // search
    private lateinit var searchViewRestaurant: SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchViewRestaurant = root.findViewById(R.id.searchRestaurantName)

        searchViewRestaurant.setOnQueryTextListener(this)

        initRecyclerView(root)
        return root
    }

    private fun initRecyclerView(view: View){
        recyclerView = view.findViewById(R.id.recyclerFoundRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        myAdapter = RestaurantAdapter(restaurantList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(requireContext(), RestaurantDetail::class.java)
            intent.putExtra("restaurantData", it)
            startActivity(intent)
        }

    }

    private fun handleSearchRestaurants(restaurantName: String) {
        Log.d("handleSearchRestaurants", "ejecutando busqueda")
        restaurantList.clear()
        db.collection("restaurants")
            .orderBy("name")
            .startAt(restaurantName)
            .endAt(restaurantName + '\uf8ff')
            .limit(10)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val item = Restaurant(
                        document.data.getValue("address").toString(),
                        document.data.getValue("name").toString(),
                        document.data.getValue("phone").toString(),
                        document.data.getValue("photo").toString(),
                        document.data.getValue("ruc").toString(),
                        document.data.getValue("specialty").toString(),
                        document.data.getValue("latitude").toString(),
                        document.data.getValue("longitude").toString(),
                        document.id,
                    )
                    restaurantList.add(item)
                    Log.d("handleSearchRestaurants", "${document.id} => ${document.data}")
                }
                myAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("handleSearchRestaurants", "Error getting documents.", exception)
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // metodos para buscador
    private var ultimaConsulta = ""

    override fun onQueryTextSubmit(query: String?): Boolean {

        if (query != null && query.length >= 3 && query != ultimaConsulta) {
            ultimaConsulta = query

            handleSearchRestaurants(query)
        }

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}