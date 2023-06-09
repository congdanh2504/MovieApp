package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.CastAndCrewBinding
import com.training.movieapp.domain.model.Crew
import com.training.movieapp.ui.main.utils.Images

class CrewAdapter(private val crews: List<Crew>, private val onPeopleClick: (Int) -> Unit) :
    RecyclerView.Adapter<CrewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: CastAndCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crew: Crew) {
            binding.apply {
                textViewName.text = crew.name
                textViewRole.text = crew.job
                imageViewProfileImage.load(
                    if (crew.profilePath == null) R.drawable.icons8user
                    else Images.POSTER_BASE_URL + crew.profilePath
                )
                linearLayoutPeople.setOnClickListener {
                    onPeopleClick(crew.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CastAndCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = crews.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val crew = crews[position]
        holder.bind(crew)
    }
}
