package com.training.movieapp.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.training.movieapp.databinding.UserItemBinding

class UserListAdapter(private val users: List<String>, private val  context: Context) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindUser(user: String, position: Int) {
            if (position == 0) {
                binding.card.radius = 25.0F
            } else if (position == users.size - 1) {
                binding.card.radius = 25.0F
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bindUser(user, position)
    }
}