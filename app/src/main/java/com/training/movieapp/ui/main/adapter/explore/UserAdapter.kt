package com.training.movieapp.ui.main.adapter.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.UserItemBinding
import com.training.movieapp.domain.model.User

class UserAdapter(private val userModel: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = UserItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            userImage.load(userModel[position].imageURL ?: R.drawable.icons8user)
            tvUserName.text = userModel[position].username
            tvUserNickName.text = "@" + userModel[position].bio
            if (position == 0) {
                card.setBackgroundResource(R.drawable.top_radius)
            } else if (position == userModel.size - 1) {
                card.setBackgroundResource(R.drawable.bottom_radius)
            }

        }
    }

    override fun getItemCount() = userModel.size
}
