package com.training.movieapp.ui.main.adapter.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.databinding.MainCompaniesItemBinding
import com.training.movieapp.databinding.MainNetworksItemBinding
import com.training.movieapp.databinding.MainPerformerItemBinding
import com.training.movieapp.databinding.MainUserItemBinding
import com.training.movieapp.ui.main.adapter.explore.ExploreAdapter.Const.COMPANIES_VIEW
import com.training.movieapp.ui.main.adapter.explore.ExploreAdapter.Const.NETWORK_VIEW
import com.training.movieapp.ui.main.adapter.explore.ExploreAdapter.Const.PERFORMER_VIEW
import com.training.movieapp.ui.main.adapter.explore.ExploreAdapter.Const.USER_VIEW
import com.training.movieapp.ui.main.utils.SampleData

class ExploreAdapter(private val listView: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    object Const {
        const val PERFORMER_VIEW = 0
        const val NETWORK_VIEW = 1
        const val COMPANIES_VIEW = 2
        const val USER_VIEW = 3
    }

    inner class MainPerformerViewHolder(private val mainPerformer: MainPerformerItemBinding) :
        RecyclerView.ViewHolder(mainPerformer.root) {
        fun bind() {
            val performerAdapter = PerformerAdapter(SampleData.Performer)
            mainPerformer.rvPerformer.adapter = performerAdapter
        }
    }

    inner class MainNetworkViewHolder(private val mainNetwork: MainNetworksItemBinding) :
        RecyclerView.ViewHolder(mainNetwork.root) {
        fun bind() {
            val networkAdapter = NetworkAdapter(SampleData.Network)
            mainNetwork.rvNetworks.adapter = networkAdapter
        }
    }

    inner class MainCompaniesViewHolder(private val mainCompanies: MainCompaniesItemBinding) :
        RecyclerView.ViewHolder(mainCompanies.root) {
        fun bind() {
            val companiesAdapter = CompaniesAdapter(SampleData.Companies)
            mainCompanies.rvCompanies.adapter = companiesAdapter
        }
    }

    inner class MainUserViewHolder(private val mainUser: MainUserItemBinding) :
        RecyclerView.ViewHolder(mainUser.root) {
        fun bind() {
            val userAdapter = UserAdapter(SampleData.User)
            mainUser.rvUser.adapter = userAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PERFORMER_VIEW -> {
                val view = MainPerformerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MainPerformerViewHolder(view)
            }

            NETWORK_VIEW -> {
                val view = MainNetworksItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MainNetworkViewHolder(view)
            }

            COMPANIES_VIEW -> {
                val view = MainCompaniesItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MainCompaniesViewHolder(view)
            }

            USER_VIEW -> {
                val view =
                    MainUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MainUserViewHolder(view)
            }

            else -> {
                throw IllegalArgumentException("invalid item type")
            }
        }
    }

    override fun getItemCount() = listView.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (listView[position]) {
            PERFORMER_VIEW -> (holder as MainPerformerViewHolder).bind()
            NETWORK_VIEW -> (holder as MainNetworkViewHolder).bind()
            COMPANIES_VIEW -> (holder as MainCompaniesViewHolder).bind()
            USER_VIEW -> (holder as MainUserViewHolder).bind()
            else -> throw IllegalArgumentException("invalid item type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listView[position]
    }
}
