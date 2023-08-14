package com.example.puntualo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puntualo.controllers.recyclerViewScores.ScoreAdapter
import com.example.puntualo.global.GlobalInfo
import com.example.puntualo.models.Restaurant
import com.example.puntualo.models.Score
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RestaurantDetail : AppCompatActivity() {

    private val db = Firebase.firestore

    lateinit var idRestaurant: String

    private lateinit var recyclerView: RecyclerView
    private var scoreList = ArrayList<Score>()
    private lateinit var myAdapter: ScoreAdapter

    // TOTAL SCORES
    private var num_scores = 0
    private var total_score = 0
    private var total_score_infrastructure = 0
    private var total_score_product = 0
    private var total_score_security = 0
    private var total_score_service = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        val restaurant = intent.getParcelableExtra<Restaurant>("restaurantData")
        if(restaurant != null){
            idRestaurant = restaurant.id.toString()
            val imgRestaurantPhoto = findViewById<ImageView>(R.id.imgRestaurantPhoto)
            val textRestaurantName = findViewById<TextView>(R.id.textRestaurantName)
            val textRestaurantSpecialty = findViewById<TextView>(R.id.textRestaurantSpecialty)
            textRestaurantName.text = restaurant.name
            textRestaurantSpecialty.text = restaurant.specialty
            Glide.with(imgRestaurantPhoto.context).load(restaurant.photo).into(imgRestaurantPhoto)


            initRecyclerViewScores(restaurant?.id.toString())
            // handleGetTotalScores(restaurant?.id.toString())
        }

        val buttonScore = findViewById<Button>(R.id.buttonScore)
        val buttonMap = findViewById<Button>(R.id.buttonMapp)
        buttonScore.setOnClickListener {
            val intent = Intent(this, QualificationForm::class.java)
            intent.putExtra("restaurantData", restaurant)
            startActivity(intent)
        }
        val latitude = restaurant?.latitude.toString().toDouble()
        val longitude = restaurant?.longitude.toString().toDouble()
        val restaurantName = restaurant?.name.toString()


        restaurant?.longitude.toString()
        buttonMap.setOnClickListener {
            val intent = Intent(this, Mapa::class.java)
            intent.putExtra(Mapa.EXTRA_LATITUDE, latitude)
            intent.putExtra(Mapa.EXTRA_LONGITUDE, longitude)
            intent.putExtra(Mapa.EXTRA_RESTAURANT_NAME, restaurantName)
            startActivity(intent)
        }



    }

    override fun onResume() {
        super.onResume()
        handleGetScores(idRestaurant)
        handleGetTotalScores(idRestaurant)
    }

    private fun initRecyclerViewScores(idRestaurant: String){
        recyclerView = findViewById(R.id.recyclerScores)
        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = ScoreAdapter(scoreList)
        recyclerView.adapter = myAdapter

        myAdapter.onDeleteClick = {
            deleteScore(it)
        }
        // handleGetScores(idRestaurant)
    }

    private fun deleteScore(score: Score) {
        db.collection("scores").document(score.id)
            .delete()
            .addOnSuccessListener {
                Log.w("deleteScore", "score eliminado")
                Toast.makeText(this, "Comentario eliminado", Toast.LENGTH_LONG).show()
                scoreList.remove(score)
                myAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "El comentario no puede ser eliminado", Toast.LENGTH_LONG).show()
                Log.w("deleteScore", "Error deleting score", e)
            }
    }

    private fun handleGetScores(idRestaurant: String) {
        scoreList.clear()
        db.collection("scores")
            .whereEqualTo("id_restaurant", idRestaurant)
            .limit(5)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val userId = document.data.getValue("id_user").toString()
                    val userName = document.data.getValue("user_name").toString()

                    val item = Score(
                        document.data.getValue("comment").toString(),
                        document.data.getValue("creation_date").toString(),
                        document.data.getValue("id_restaurant").toString(),
                        document.data.getValue("score_infrastructure").toString().toInt(),
                        document.data.getValue("score_product").toString().toInt(),
                        document.data.getValue("score_security").toString().toInt(),
                        document.data.getValue("score_service").toString().toInt(),
                        document.data.getValue("update_date").toString(),
                        userId,
                        userName,
                        document.id
                    )
                    scoreList.add(item)
                    Log.d("handleGetScores", "${document.id} => ${document.data}")
                }
                myAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("handleGetScores", "Error getting documents.", exception)
            }
    }

    fun getStars(score: Int): String {
        return "⭐⭐⭐⭐⭐".subSequence(0, score).toString()
    }

    private fun handleGetTotalScores(idRestaurant: String) {
        val textTotalScore = findViewById<TextView>(R.id.textTotalScore)
        val textTotalScoreStars = findViewById<TextView>(R.id.textTotalScoreStars)
        val textNumScore = findViewById<TextView>(R.id.textNumScore)

        val textTotalScoreInfrastructure = findViewById<TextView>(R.id.textTotalScoreInfrastructure)
        val textTotalScoreProduct = findViewById<TextView>(R.id.textTotalScoreProduct)
        val textTotalScoreSecurity = findViewById<TextView>(R.id.textTotalScoreSecurity)
        val textTotalScoreService = findViewById<TextView>(R.id.textTotalScoreService)

        num_scores = 0
        total_score_infrastructure = 0
        total_score_service = 0
        total_score_security = 0
        total_score_product = 0

        db.collection("scores")
            .whereEqualTo("id_restaurant", idRestaurant)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val userId = document.data.getValue("id_user").toString()
                    val userName = document.data.getValue("user_name").toString()
                    val item = Score(
                        document.data.getValue("comment").toString(),
                        document.data.getValue("creation_date").toString(),
                        document.data.getValue("id_restaurant").toString(),
                        document.data.getValue("score_infrastructure").toString().toInt(),
                        document.data.getValue("score_product").toString().toInt(),
                        document.data.getValue("score_security").toString().toInt(),
                        document.data.getValue("score_service").toString().toInt(),
                        document.data.getValue("update_date").toString(),
                        userId,
                        userName,
                        document.id
                    )

                    // 1. calcular totales
                    num_scores += 1
                    total_score_infrastructure += item.score_infrastructure
                    total_score_service += item.score_service
                    total_score_security += item.score_security
                    total_score_product += item.score_product

                    Log.d("handleGetTotalScores", "${document.id} => ${document.data}")
                }

                if(num_scores > 0) {
                    // 1. calcular promedio
                    total_score_infrastructure = total_score_infrastructure / num_scores
                    total_score_service = total_score_service / num_scores
                    total_score_security = total_score_security / num_scores
                    total_score_product = total_score_product / num_scores
                    total_score = (total_score_infrastructure + total_score_service + total_score_security + total_score_product) / 4

                    textTotalScore.text = total_score.toString()
                    textTotalScoreStars.text = getStars(total_score)
                    textNumScore.text = num_scores.toString()

                    textTotalScoreInfrastructure.text = "Infraestructura ${getStars(total_score_infrastructure)}"
                    textTotalScoreProduct.text = "Platillo ${getStars(total_score_service)}"
                    textTotalScoreSecurity.text = "Seguridad ${getStars(total_score_security)}"
                    textTotalScoreService.text = "Atención al cliente ${getStars(total_score_product)}"
                } else {
                    textNumScore.text = "Sin calificaciones"
                }
            }
            .addOnFailureListener { exception ->
                Log.w("handleGetTotalScores", "Error getting documents.", exception)
            }
    }
}