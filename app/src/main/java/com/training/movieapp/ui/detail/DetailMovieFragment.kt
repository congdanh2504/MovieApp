package com.training.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.training.movieapp.R
import com.training.movieapp.common.LoadingDialog
import com.training.movieapp.common.viewBinding
import com.training.movieapp.databinding.FragmentDetailMovieBinding
import com.training.movieapp.domain.model.Company
import com.training.movieapp.domain.model.Credit
import com.training.movieapp.domain.model.Movie
import com.training.movieapp.domain.model.MovieDetail
import com.training.movieapp.domain.model.VideoResponse
import com.training.movieapp.domain.model.state.DataState
import com.training.movieapp.ui.detail.adapter.CastAndCrewAdapter
import com.training.movieapp.ui.detail.adapter.CompanyAdapter
import com.training.movieapp.ui.detail.viewmodel.DetailMovieViewModel
import com.training.movieapp.ui.main.adapter.movie.MovieAdapter
import com.training.movieapp.ui.main.utils.Images
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val detailMovieViewModel: DetailMovieViewModel by viewModels()
    private val binding: FragmentDetailMovieBinding by viewBinding(FragmentDetailMovieBinding::bind)
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var castAndCrewAdapter: CastAndCrewAdapter
    private lateinit var dialog: LoadingDialog
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieViewModel.getDetailMovie(args.movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        initActions()
    }

    private fun initView() {
        dialog = LoadingDialog(requireContext())
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailMovieViewModel.movieDetailState.collect { state ->
                        when (state) {
                            is DataState.Idle -> {
                                dialog.dismiss()
                            }

                            is DataState.Loading -> {
                                dialog.show()
                            }

                            is DataState.Success -> {
                                dialog.dismiss()
                                setMovieDetail(state.data)
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
                launch {
                    detailMovieViewModel.videosState.collect { state ->
                        when (state) {
                            is DataState.Success -> {
                                setVideo(state.data)
                            }

                            is DataState.Error -> {
                                Toast.makeText(
                                    requireContext(),
                                    state.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

    private fun initActions() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setMovieDetail(movieDetail: MovieDetail) {
        setMovie(movieDetail.movie)
        setCredit(movieDetail.credit)
        setCompanies(movieDetail.movie.productionCompanies)
        setSimilar(movieDetail.similar.results)
        detailMovieViewModel.getVideos(args.movieId)
    }

    private fun setMovie(movie: Movie) {
        binding.apply {
            movie.posterPath?.let { imageViewMovieImage.load(Images.POSTER_BASE_URL + it) }
            textViewTitle.text = movie.title
            textViewReleaseDate.text = movie.releaseDate
            textViewStatus.text = movie.status
            movie.runtime?.let { textViewRuntime.text = formatMinutesToTime(it) }
            textViewLanguages.text = movie.originalLanguage
            textViewGenres.text = movie.genres.joinToString(", ") { it.name }
            textViewBudget.text = "$${movie.budget}"
            textViewRevenue.text = "$${movie.revenue}"
            readMoreTextViewSynopsis.text = movie.overview.toString()
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

    private fun setCompanies(companies: List<Company>) {
        companyAdapter = CompanyAdapter(companies, onCompanyClick)
        binding.numberCompanies.text = companies.size.toString()
        binding.recyclerViewCompanies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCompanies.adapter = companyAdapter
    }

    private fun setSimilar(movies: List<Movie>) {
        movieAdapter = MovieAdapter(onMovieClick)
        movieAdapter.setMovies(movies)
        binding.recyclerViewMovie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewMovie.adapter = movieAdapter
    }

    private fun setVideo(videoResponse: VideoResponse) {
        val videos = videoResponse.videos
        val trailerVideo =
            videos.firstOrNull { video -> video.type == "Trailer" && video.site == "YouTube" }
        trailerVideo?.let {
            binding.youtubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                    binding.youtubePlayerView.isGone = false
                    val videoId = it.key
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
    }

    private val onMovieClick: (movie: Movie) -> Unit = { movie ->
        val action = DetailMovieFragmentDirections.actionDetailMovieFragmentSelf(movie.id)
        findNavController().navigate(action)
    }

    private val onCompanyClick: (companyId: Int) -> Unit = { companyId ->
        val action =
            DetailMovieFragmentDirections.actionDetailMovieFragmentToDetailCompanyFragment(
                companyId = companyId
            )
        findNavController().navigate(action)
    }

    private val onPeopleClick: (peopleId: Int) -> Unit = { peopleId ->
        val action =
            DetailMovieFragmentDirections.actionDetailMovieFragmentToDetailPersonFragment(
                peopleId
            )
        findNavController().navigate(action)
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

    private fun getTab(number: Int, title: String): LinearLayout {
        val tab = LayoutInflater.from(requireContext())
            .inflate(R.layout.cast_tab_title, null) as LinearLayout
        tab.findViewById<TextView>(R.id.number).text = number.toString()
        tab.findViewById<TextView>(R.id.title).text = title
        return tab
    }
}
