package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.R
import com.training.movieapp.ui.detail.model.MovieItems
import com.training.movieapp.ui.detail.model.PageItems

class MyPagerAdapter(private val data: List<PageItems>) : RecyclerView.Adapter<MyPagerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (data[position] is MovieItems) {
            val movies: List<String> = listOf(
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg",
                "https://image.tmdb.org/t/p/w500/vuZrgFo96FGiTJC489aPtWyTDt2.jpg"
            )
            val adapter = MovieListAdapter(movies, holder.recyclerView.context)
            holder.recyclerView.layoutManager = GridLayoutManager(holder.recyclerView.context, 2)
            holder.recyclerView.adapter = adapter
        } else {
            val users = listOf("asd", "Asd", "asd")
            val adapter = UserListAdapter(users, holder.recyclerView.context)
            holder.recyclerView.layoutManager = LinearLayoutManager(holder.recyclerView.context)
            holder.recyclerView.adapter = adapter
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
