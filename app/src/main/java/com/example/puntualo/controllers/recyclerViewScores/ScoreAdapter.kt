package com.example.puntualo.controllers.recyclerViewScores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puntualo.R
import com.example.puntualo.controllers.recyclerViewScores.ScoreViewHolder
import com.example.puntualo.global.GlobalInfo
import com.example.puntualo.models.Restaurant
import com.example.puntualo.models.Score

class ScoreAdapter(private val scoreList: List<Score>): RecyclerView.Adapter<ScoreViewHolder>(){

    var onDeleteClick: ((Score) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ScoreViewHolder(layoutInflater.inflate(R.layout.item_score, parent, false))
    }

    override fun getItemCount(): Int {
        return scoreList.size
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val item = scoreList[position]
        val user = GlobalInfo.user
        val userType = if(user !== null) user.type else "customer"

        // NOTE eliminar comentario
        holder.btnDeleteScore.setOnClickListener{
            onDeleteClick?.invoke(item)
        }
        holder.render(item, userType)
    }
}