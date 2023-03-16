package com.training.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.movieapp.R
import com.training.movieapp.databinding.FragmentDetailPersonBinding

class DetailPersonFragment : Fragment() {

    private lateinit var detailPersonBinding: FragmentDetailPersonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailPersonBinding = FragmentDetailPersonBinding.inflate(inflater, container, false)
        return detailPersonBinding.root
    }

}