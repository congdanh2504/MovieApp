package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.CastAndCrewBinding
import com.training.movieapp.domain.model.Cast
import com.training.movieapp.ui.main.utils.Images

class CastAdapter(private val casts: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: CastAndCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: Cast) {
            binding.apply {
                textViewName.text = cast.name
                textViewRole.text = cast.character
                imageViewProfileImage.load(
                    if (cast.profilePath == null) R.drawable.icons8user
                    else Images.POSTER_BASE_URL + cast.profilePath
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CastAndCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = casts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cast = casts[position]
        holder.bind(cast)
    }
}
