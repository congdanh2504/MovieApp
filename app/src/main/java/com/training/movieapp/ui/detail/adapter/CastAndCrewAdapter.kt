package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.R
import com.training.movieapp.domain.model.Cast
import com.training.movieapp.domain.model.Crew
import com.training.movieapp.domain.model.People

class CastAndCrewAdapter(
    private val casts: List<Cast>,
    private val crews: List<Crew>,
    private val onPeopleClick: (Int) -> Unit
) :
    RecyclerView.Adapter<CastAndCrewAdapter.MyViewHolder>() {
    companion object {
        private const val CAST_POSITION = 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = 2

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (position == CAST_POSITION) {
            val adapter = CastAdapter(casts, onPeopleClick)
            holder.recyclerView.layoutManager = LinearLayoutManager(
                holder.recyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            holder.recyclerView.adapter = adapter
        } else {
            val adapter = CrewAdapter(crews, onPeopleClick)
            holder.recyclerView.layoutManager = LinearLayoutManager(
                holder.recyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            holder.recyclerView.adapter = adapter
        }
    }
}
