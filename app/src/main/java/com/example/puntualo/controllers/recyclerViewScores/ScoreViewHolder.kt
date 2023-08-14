package com.example.puntualo.controllers.recyclerViewScores

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.puntualo.R
import com.example.puntualo.models.Score
import java.text.SimpleDateFormat
import java.util.*

class ScoreViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val imgUserPhoto = view.findViewById<ImageView>(R.id.imgUserPhoto)
    val textUserName = view.findViewById<TextView>(R.id.textUserName)
    val textScoreDate = view.findViewById<TextView>(R.id.textScoreDate)
    val textScoreComment = view.findViewById<TextView>(R.id.textScoreComment)
    val textScoreStars = view.findViewById<TextView>(R.id.textScoreStars)
    val btnDeleteScore = view.findViewById<Button>(R.id.btnDeleteScore)


    fun getAverageScore(score: Score): Int {
        return (score.score_infrastructure + score.score_product + score.score_security + score.score_service) / 4
    }

    fun getStars(score: Int): String {
        return "⭐⭐⭐⭐⭐".subSequence(0, score).toString()
    }

    fun render(score: Score, userType: String) {
        val averageScore = getAverageScore(score)
        val stars = getStars(averageScore)

        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(Date())
        Log.d("render", "${userType}")
        if (userType == "admin") {
            Log.d("render true", "${userType}")
            btnDeleteScore.visibility = View.VISIBLE
        } else {
            btnDeleteScore.visibility = View.INVISIBLE
        }

        textUserName.text = score.user_name
        textScoreDate.text = date
        textScoreComment.text = score.comment
        textScoreStars.text = stars
    }
}