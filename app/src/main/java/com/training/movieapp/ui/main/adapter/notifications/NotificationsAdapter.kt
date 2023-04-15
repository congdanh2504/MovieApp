package com.training.movieapp.ui.main.adapter.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.UserItemNotificationsBinding
import com.training.movieapp.ui.main.model.User

class NotificationsAdapter(private val userModel: List<User>):
    RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder>() {
    inner class NotificationsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = UserItemNotificationsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item_notifications, parent, false)
        return NotificationsViewHolder(view)
    }

    override fun getItemCount() = userModel.size

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        holder.binding.apply {
            userImage.load(userModel[position].imageUrl)
            tvUsername.text = userModel[position].name
            tvNickname.text = "@"+userModel[position].name
        }
    }
}