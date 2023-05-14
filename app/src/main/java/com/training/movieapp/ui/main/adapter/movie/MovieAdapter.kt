package com.training.movieapp.ui.main.adapter.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.MovieItemBinding
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.ui.main.utils.Images

open class MovieAdapter(
    private val onMovieClick: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    protected val movies: ArrayList<Movie> = arrayListOf()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = MovieItemBinding.bind(itemView)
    }

    class MovieCallback(private val oldList: List<Movie>, private val newList: List<Movie>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            imgMoviePoster.load(
                if (movies[position].posterPath != null)
                    Images.POSTER_BASE_URL + movies[position].posterPath
                else R.drawable.noimage
            )
            imgMoviePoster.setOnClickListener {
                onMovieClick(movies[position])
            }
        }
    }

    override fun getItemCount() = movies.size

    open fun setMovies(newList: List<Movie>) {
        val diffCallback = MovieCallback(movies, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        movies.clear()
        movies.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}
