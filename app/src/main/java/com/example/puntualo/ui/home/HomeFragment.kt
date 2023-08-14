package com.example.puntualo.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.puntualo.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puntualo.models.Restaurant
import com.example.puntualo.R
import com.example.puntualo.RestaurantDetail
import com.example.puntualo.controllers.recyclerViewRestaurants.RestaurantAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val db = Firebase.firestore

    private lateinit var recyclerView: RecyclerView
    private var restaurantList = ArrayList<Restaurant>()
    private lateinit var myAdapter: RestaurantAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        initRecyclerView(root)
        return root
    }

    private fun initRecyclerView(view: View){
        recyclerView = view.findViewById(R.id.recyclerRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        myAdapter = RestaurantAdapter(restaurantList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(requireContext(), RestaurantDetail::class.java)
            intent.putExtra("restaurantData", it)
            startActivity(intent)
        }

        handleGetRestaurants()
        //recyclerView.adapter = RestaurantAdapter(RestaurantMock.restaurantList)
    }

    private fun handleGetRestaurants() {
        restaurantList.clear()
        db.collection("restaurants")
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
                    Log.d("handleGetRestaurants", "${document.id} => ${document.data}")
                }
                myAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("handleGetRestaurants", "Error getting documents.", exception)
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}