package com.training.movieapp.ui.main.adapter.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.PerformerItemBinding
import com.training.movieapp.ui.main.model.Performer

class PerformerAdapter(private val performerModel: List<Performer>) :
    RecyclerView.Adapter<PerformerAdapter.PerformerViewHolder>() {
    inner class PerformerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PerformerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.performer_item, parent, false)
        return PerformerViewHolder(view)
    }

    override fun getItemCount() = performerModel.size

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        holder.binding.apply {
            tvNamePerformer.text = performerModel[position].name
            imgPerformer.load(performerModel[position].imageUrl)
        }
    }
}
