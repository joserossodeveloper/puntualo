package com.example.puntualo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.example.puntualo.global.GlobalInfo
import com.example.puntualo.models.Restaurant
import com.example.puntualo.models.Score
import com.example.puntualo.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QualificationForm : AppCompatActivity() {

    private val db = Firebase.firestore
    lateinit var restaurant: Restaurant
    private var user: User? = null

    lateinit var ratingBarSecurity: RatingBar
    lateinit var ratingBarInfrastructure: RatingBar
    lateinit var ratingBarProduct: RatingBar
    lateinit var ratingBarService: RatingBar
    lateinit var textScoreComment: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qualification_form)

        val restaurantData = intent.getParcelableExtra<Restaurant>("restaurantData")
        if(restaurantData != null) {
            restaurant = restaurantData
        }

        ratingBarSecurity = findViewById<RatingBar>(R.id.ratingBarSecurity)
        ratingBarInfrastructure = findViewById<RatingBar>(R.id.ratingBarInfrastructure)
        ratingBarProduct = findViewById<RatingBar>(R.id.ratingBarProduct)
        ratingBarService = findViewById<RatingBar>(R.id.ratingBarService)
        textScoreComment = findViewById<EditText>(R.id.editTextTextMultiLine)

        user = GlobalInfo.user

        val buttonCreateScore = findViewById<Button>(R.id.buttonCreateScore)
        buttonCreateScore.setOnClickListener {
            createScore()
        }
    }

    fun createScore(){

        val ratingInfrastructure = ratingBarInfrastructure.rating.toInt()
        val ratingProduct = ratingBarProduct.rating.toInt()
        val ratingSecurity = ratingBarSecurity.rating.toInt()
        val ratingService = ratingBarService.rating.toInt()

        if (ratingInfrastructure < 1 || ratingProduct < 1 || ratingSecurity < 1 || ratingService < 1) {
            Toast.makeText(this, "Agregue al menos una estrella de calificación", Toast.LENGTH_LONG).show()
            return
        }

        if (user === null) {
            Toast.makeText(this, "Necesita iniciar sesión", Toast.LENGTH_LONG).show()
            return
        }


        val score = Score(
            textScoreComment.text.toString(),
            "",
            restaurant.id!!,
            ratingInfrastructure,
            ratingProduct,
            ratingSecurity,
            ratingService,
            "",
            user!!.id,
            user!!.name,
            ""
        )

        db.collection("scores")
            .add(score)
            .addOnSuccessListener {
                finish()
                Log.d("createScore", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("createScore", "Error writing document", e)
                Toast.makeText(this, "Error al registrar calificación", Toast.LENGTH_LONG).show()
            }
    }
}