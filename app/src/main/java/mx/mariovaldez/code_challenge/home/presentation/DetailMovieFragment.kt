package mx.mariovaldez.code_challenge.home.presentation

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.code_challenge.R


@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {


    companion object {
        fun newInstance(): DetailMovieFragment = DetailMovieFragment()
    }
}
