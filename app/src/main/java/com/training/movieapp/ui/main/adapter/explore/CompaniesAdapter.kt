package com.training.movieapp.ui.main.adapter.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.NetworkAndCompaniesItemBinding
import com.training.movieapp.ui.main.model.Companies

class CompaniesAdapter(private val companiesModel:List<Companies>):RecyclerView.Adapter<CompaniesAdapter.CompaniesViewHolder>() {
    inner class CompaniesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val binding = NetworkAndCompaniesItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompaniesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.network_and_companies_item,parent,false)
        return CompaniesViewHolder(view)
    }

    override fun getItemCount() = companiesModel.size

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.binding.apply {
            imgLogo.load(companiesModel[position].imageUrl)
        }
    }
}