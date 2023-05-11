package com.training.movieapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.databinding.CompanyItemBinding
import com.training.movieapp.domain.model.Company
import com.training.movieapp.ui.main.utils.Images

class CompanyAdapter(
    private val companies: List<Company>,
    private val onCompanyClick: (Int) -> Unit
) :
    RecyclerView.Adapter<CompanyAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: CompanyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(company: Company) {
            binding.apply {
                imageViewCompanyImage.load(
                    Images.POSTER_BASE_URL + company.logoPath
                )
                imageViewCompanyImage.setOnClickListener {
                    onCompanyClick(company.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CompanyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = companies.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val crew = companies[position]
        holder.bind(crew)
    }
}
