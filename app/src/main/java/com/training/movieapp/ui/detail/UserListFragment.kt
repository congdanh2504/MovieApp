package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.movieapp.databinding.FragmentUserListBinding
import com.training.movieapp.ui.detail.adapter.UserListAdapter


class UserListFragment : Fragment() {

    private lateinit var userListBinding: FragmentUserListBinding
    private lateinit var adapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userListBinding = FragmentUserListBinding.inflate(inflater, container, false)
        return userListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val users = listOf("asd", "Asd", "asd")
        adapter = UserListAdapter(users, requireContext())
        userListBinding.userRecycler.layoutManager = LinearLayoutManager(requireContext())
        userListBinding.userRecycler.adapter = adapter
        userListBinding.userRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

}