package com.training.movieapp.ui.main.adapter.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.NetworkAndCompaniesItemBinding
import com.training.movieapp.ui.main.model.Network

class NetworkAdapter(private val networkModel: List<Network>) :
    RecyclerView.Adapter<NetworkAdapter.NetworkViewHolder>() {
    inner class NetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = NetworkAndCompaniesItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.network_and_companies_item, parent, false)
        return NetworkViewHolder(view)
    }

    override fun getItemCount() = networkModel.size

    override fun onBindViewHolder(holder: NetworkViewHolder, position: Int) {
        holder.binding.apply {
            imgLogo.load(networkModel[position].imageUrl)
        }
    }
}
