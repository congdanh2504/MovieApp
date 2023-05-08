package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.R
import com.training.movieapp.domain.model.MovieCredit

class MyPagerAdapter(
    private val cast: List<MovieCredit>,
    private val crew: List<MovieCredit>,
    private val onMovieClick: (Int) -> Unit
) : RecyclerView.Adapter<MyPagerAdapter.MyViewHolder>() {

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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.recyclerView.layoutManager = GridLayoutManager(holder.recyclerView.context, 2)
        if (position == CAST_POSITION) {
            val adapter = MovieListAdapter(cast, onMovieClick)
            holder.recyclerView.adapter = adapter
        } else {
            val adapter = MovieListAdapter(crew, onMovieClick)
            holder.recyclerView.adapter = adapter
        }
    }

    override fun getItemCount() = 2
}
