package mx.mariovaldez.code_challenge.home.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.code_challenge.R
import mx.mariovaldez.code_challenge.databinding.FragmentListMoviesBinding
import mx.mariovaldez.code_challenge.home.presentation.adapters.MoviesAdapter
import mx.mariovaldez.code_challenge.ktx.exhaustive
import mx.mariovaldez.code_challenge.ktx.invisible
import mx.mariovaldez.code_challenge.ktx.observe
import mx.mariovaldez.code_challenge.ktx.viewBinding
import mx.mariovaldez.code_challenge.ui.bind

@AndroidEntryPoint
class ListMoviesFragment : Fragment(R.layout.fragment_list_movies) {

    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: ListMoviesViewModel by viewModels()

    private val binding: FragmentListMoviesBinding by viewBinding(
        FragmentListMoviesBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()

        viewModel.findMovies()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: ListMoviesViewModel.State) {
        when (state) {
            ListMoviesViewModel.State.Loading -> showProgress()
            is ListMoviesViewModel.State.ShowData -> {
                moviesAdapter.addMovies(state.movies)
            }
            ListMoviesViewModel.State.ShowError -> showError()
        }.exhaustive
    }

    private fun showError() {
        //implement required logic
    }

    private fun showProgress() {
        //implement required logic
    }

    private fun setupViews() {
        moviesAdapter = MoviesAdapter { moviesPosition -> }
        with(binding) {
            toolbar.bind(
                R.string.list_movies_frag_title
            ) { }
            toolbar.backButton.invisible()

            listMoviesRecyclerView.apply {
                adapter = moviesAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireActivity(),
                        DividerItemDecoration.VERTICAL,
                    )
                )
                setHasFixedSize(true)
            }

        }
    }


    companion object {

        fun newInstance(): ListMoviesFragment = ListMoviesFragment()
    }
}