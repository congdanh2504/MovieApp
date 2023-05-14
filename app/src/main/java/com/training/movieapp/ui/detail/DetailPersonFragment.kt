package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentDetailPersonBinding
import com.training.movieapp.domain.model.MovieCredits
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.detail.adapter.MyPagerAdapter
import com.training.movieapp.ui.detail.model.PeopleDetail
import com.training.movieapp.ui.detail.viewmodel.DetailPeopleViewModel
import com.training.movieapp.ui.main.utils.Images
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DetailPersonFragment : Fragment(R.layout.fragment_detail_person) {

    private val binding: FragmentDetailPersonBinding by viewBinding(FragmentDetailPersonBinding::bind)
    private lateinit var adapter: MyPagerAdapter
    private val detailPeopleViewModel: DetailPeopleViewModel by viewModels()
    private val args: DetailPersonFragmentArgs by navArgs()
    private lateinit var dialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailPeopleViewModel.getPeopleDetail(args.peopleId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
//        binding.viewPager.reduceDragSensitivity()
        dialog = LoadingDialog(requireContext())
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailPeopleViewModel.peopleState.collect { state ->
                    when (state) {
                        is DataState.Idle -> {
                            dialog.dismiss()
                        }

                        is DataState.Loading -> {
                            dialog.show()
                        }

                        is DataState.Success -> {
                            dialog.dismiss()
                            setPeopleDetail(state.data)
                        }

                        is DataState.Error -> {
                            dialog.dismiss()
                            Toast.makeText(
                                requireContext(),
                                state.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setPeopleDetail(peopleDetail: PeopleDetail) {
        binding.apply {
            imageViewProfileImage.load(
                if (peopleDetail.profilePath == null) R.drawable.icons8user
                else Images.POSTER_BASE_URL + peopleDetail.profilePath
            )
            textViewName.text = peopleDetail.name
            textViewRole.text = peopleDetail.knownForDepartment
            textViewBio.text = peopleDetail.biography
            val born = (peopleDetail.birthday ?: "") + ", " + (peopleDetail.placeOfBirth ?: "")
            textViewBorn.text = born
            peopleDetail.birthday?.let {
                textViewAge.text = birthDateToAge(it).toString()
            }
            setTabLayout(peopleDetail.movieCredits)
        }
    }

    private fun birthDateToAge(birthdateString: String): Int {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthdate = LocalDate.parse(birthdateString, formatter)
        return Period.between(birthdate, LocalDate.now()).years
    }

    private fun setTabLayout(movieCredits: MovieCredits) {
        adapter = MyPagerAdapter(movieCredits.cast, movieCredits.crew, onMovieClick)
        binding.viewPager.adapter = adapter

        val tabTitles =
            listOf(getTab(movieCredits.cast.size, "Cast"), getTab(movieCredits.crew.size, "Crew"))

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
    }

    private val onMovieClick: (movieId: Int) -> Unit = { movieId ->
        val action =
            DetailPersonFragmentDirections.actionDetailPersonFragmentToDetailMovieFragment(movieId)
        findNavController().navigate(action)
    }

    private fun getTab(number: Int, title: String): LinearLayout {
        val tab = LayoutInflater.from(requireContext())
            .inflate(R.layout.custom_tab_title, null) as LinearLayout
        tab.findViewById<TextView>(R.id.number).text = number.toString()
        tab.findViewById<TextView>(R.id.title).text = title
        return tab
    }

    fun ViewPager2.reduceDragSensitivity() {
        val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(this) as RecyclerView

        val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int

        touchSlopField.set(recyclerView, touchSlop*8)       // "8" was obtained experimentally
    }
}
