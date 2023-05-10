package com.training.movieapp.ui.main.adapter.movie

import com.training.movieapp.domain.model.People

class PaginationPeopleAdapter(
    onPeopleClick: (People) -> Unit,
    private val onLoadMorePeoples: (Int) -> Unit
) :
    PeopleAdapter(onPeopleClick) {

    private var page = 1
    var totalResult = 0

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position + 1 == peoples.size && peoples.size < totalResult) {
            onLoadMorePeoples(++page)
        }
    }

    override fun setPeoples(newList: List<People>) {
        super.setPeoples(newList)
        page = 1
    }

    fun addPeoples(newList: List<People>) {
        peoples.addAll(newList)
        notifyItemInserted(peoples.size)
    }
}
