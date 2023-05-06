package com.training.movieapp.ui.main.adapter.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.training.movieapp.R
import com.training.movieapp.databinding.PeopleItemBinding
import com.training.movieapp.domain.model.People
import com.training.movieapp.ui.main.utils.Images

class PeopleAdapter(
    private val onPeopleClick: (People) -> Unit
) :
    RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    private val peoples: ArrayList<People> = arrayListOf()

    inner class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = PeopleItemBinding.bind(itemView)
    }

    class PeopleCallback(private val oldList: List<People>, private val newList: List<People>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] === newList[newItemPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.people_item, parent, false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.binding.apply {
            imageViewProfileImage.load(
                if (peoples[position].profilePath == null) R.drawable.icons8user
                else Images.POSTER_BASE_URL + peoples[position].profilePath
            )
            textViewName.text = peoples[position].name
            imageViewProfileImage.setOnClickListener {
                onPeopleClick(peoples[position])
            }
        }
    }

    override fun getItemCount() = peoples.size

    fun setPeoples(newList: List<People>) {
        val diffCallback = PeopleCallback(peoples, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        peoples.clear()
        peoples.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}