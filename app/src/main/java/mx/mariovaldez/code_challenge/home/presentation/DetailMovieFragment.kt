package mx.mariovaldez.code_challenge.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.code_challenge.BuildConfig
import mx.mariovaldez.code_challenge.R
import mx.mariovaldez.code_challenge.databinding.FragmentDetailMovieBinding
import mx.mariovaldez.code_challenge.home.presentation.DetailMoviewViewModel.Companion.MOVIE_KEY
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI
import mx.mariovaldez.code_challenge.home.presentation.models.VideoResultUI
import mx.mariovaldez.code_challenge.ktx.observe
import mx.mariovaldez.code_challenge.ktx.viewBinding


@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    lateinit var movieVideoInfo: VideoResultUI
    private val viewModel: DetailMoviewViewModel by viewModels()

    private val binding: FragmentDetailMovieBinding by viewBinding(
        FragmentDetailMovieBinding::bind
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            val movie = it?.getSerializable(MOVIE_KEY) as MovieUI
            viewModel.setMovie(movie)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        setupObservers()
        val movie = viewModel.getMovie()
        with(binding) {
            with(movie) {
                Glide.with(requireContext())
                    .load("${BuildConfig.BASE_URL_IMAGES}${backdropPath}")
                    .placeholder(R.drawable.marvel_icon)
                    .circleCrop()
                    .error(R.drawable.marvel_icon)
                    .into(binding.movieImageView)
                movieTitleTextView.text = originalTitle
                rateTextView.text = voteAverage.toString()
                overviewDescriptionTextView  .text = overview

            }
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = movieVideoInfo.key
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.pause()
                }
            })
        }
        viewModel.searchVideoInfo()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: DetailMoviewViewModel.State) {
        when (state) {
            DetailMoviewViewModel.State.DefaultState -> {}
            DetailMoviewViewModel.State.ShowError -> {}
            is DetailMoviewViewModel.State.ShowPlayer -> {
                movieVideoInfo = state.videoResultUI
            }
        }
    }

    override fun onStop() {
        binding.youtubePlayerView.release()
        super.onStop()
    }


    companion object {
        fun newInstance(movie: MovieUI): DetailMovieFragment = DetailMovieFragment().apply {
            arguments = bundleOf(
                MOVIE_KEY to movie
            )
        }
    }
}
