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
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.reduceDragSensitivity
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentDetailSeriesBinding
import com.training.movieapp.domain.model.Company
import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.Season
import com.training.movieapp.domain.model.Series
import com.training.movieapp.domain.model.SeriesDetail
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.detail.adapter.CastAndCrewAdapter
import com.training.movieapp.ui.detail.adapter.CompanyAdapter
import com.training.movieapp.ui.detail.adapter.SeasonAdapter
import com.training.movieapp.ui.detail.adapter.SeriesListAdapter
import com.training.movieapp.ui.detail.model.SeriesDetailView
import com.training.movieapp.ui.detail.viewmodel.DetailSeriesViewModel
import com.training.movieapp.ui.main.utils.Images
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailSeriesFragment : Fragment(R.layout.fragment_detail_series) {

    private val binding: FragmentDetailSeriesBinding by viewBinding(FragmentDetailSeriesBinding::bind)
    private val detailSeriesViewModel: DetailSeriesViewModel by viewModels()
    private val args: DetailSeriesFragmentArgs by navArgs()
    private lateinit var similarSeriesAdapter: SeriesListAdapter
    private lateinit var seasonAdapter: SeasonAdapter
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var castAndCrewAdapter: CastAndCrewAdapter
    private lateinit var dialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailSeriesViewModel.getSeriesDetail(args.seriesId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initActions()
        initObservers()
    }

    private fun initView() {
        binding.viewPager.reduceDragSensitivity()
        dialog = LoadingDialog(childFragmentManager)
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
                detailSeriesViewModel.detailSeriesState.collect { state ->
                    when (state) {
                        is DataState.Idle -> {
                            dialog.safeDismiss()
                        }

                        is DataState.Loading -> {
                            dialog.show()
                        }

                        is DataState.Success -> {
                            dialog.safeDismiss()
                            setSeriesDetail(state.data)
                        }

                        is DataState.Error -> {
                            dialog.safeDismiss()
                            Toast.makeText(
                                requireContext(), state.message.toString(), Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun setSeriesDetail(seriesDetailView: SeriesDetailView) {
        setSeries(seriesDetailView.seriesDetail)
        setCredit(seriesDetailView.credits)
        setSimilarSeries(seriesDetailView.similarSeries)
    }

    private fun setSeries(series: SeriesDetail) {
        binding.apply {
            imageViewSeriesImage.load(if (series.posterPath != null) Images.POSTER_BASE_URL + series.posterPath else R.drawable.noimage)
            textViewName.text = series.name
            textViewAirs.text = series.firstAirDate
            textViewRuntime.text = formatMinutesToTime(series.episodeRunTime.sum().toLong())
            textViewLanguages.text = series.languages.joinToString(", ") { it }
            textViewGenres.text = series.genres.joinToString(", ") { it.name }
            readMoreTextViewSynopsis.text = series.overview
            setCompanies(series.productionCompanies)
            setSeason(series.seasons)
        }
    }

    private fun setCompanies(companies: List<Company>) {
        companyAdapter = CompanyAdapter(companies, onCompanyClick)
        binding.numberCompanies.text = companies.size.toString()
        binding.recyclerViewCompanies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCompanies.adapter = companyAdapter
    }

    private fun formatMinutesToTime(minutes: Long): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return when {
            hours > 1 -> String.format("%d hours %02d minutes", hours, remainingMinutes)
            hours == 1L -> String.format("1 hour %02d minutes", remainingMinutes)
            else -> String.format("%d minutes", remainingMinutes)
        }
    }

    private fun setCredit(credit: Credit) {
        castAndCrewAdapter = CastAndCrewAdapter(credit.casts, credit.crews, onPeopleClick)
        val tabTitles =
            listOf(getTab(credit.casts.size, "Cast"), getTab(credit.crews.size, "Crew"))

        binding.viewPager.adapter = castAndCrewAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.customView = tabTitles[position]
        }.attach()
    }

    private val onCompanyClick: (companyId: Int) -> Unit = { companyId ->
        val action =
            DetailSeriesFragmentDirections.actionSeriesDetailFragmentToDetailCompanyFragment(
                companyId
            )
        findNavController().navigate(action)
    }

    private val onPeopleClick: (peopleId: Int) -> Unit = { peopleId ->
        val action =
            DetailSeriesFragmentDirections.actionSeriesDetailFragmentToDetailPersonFragment(peopleId)
        findNavController().navigate(action)
    }

    private fun getTab(number: Int, title: String): LinearLayout {
        val tab = LayoutInflater.from(requireContext())
            .inflate(R.layout.cast_tab_title, null) as LinearLayout
        tab.findViewById<TextView>(R.id.number).text = number.toString()
        tab.findViewById<TextView>(R.id.title).text = title
        return tab
    }

    private fun setSimilarSeries(series: List<Series>) {
        similarSeriesAdapter = SeriesListAdapter(series, onSeriesClick)
        binding.recyclerViewSeries.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSeries.adapter = similarSeriesAdapter
    }

    private val onSeriesClick: (seriesId: Int) -> Unit = { seriesId ->
        val action =
            DetailSeriesFragmentDirections.actionSeriesDetailFragmentSelf(
                seriesId
            )
        findNavController().navigate(action)
    }

    private fun setSeason(seasons: List<Season>) {
        seasonAdapter = SeasonAdapter(seasons)
        binding.numberSeasons.text = seasons.size.toString()
        binding.recyclerViewSeasons.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewSeasons.adapter = seasonAdapter
    }
}