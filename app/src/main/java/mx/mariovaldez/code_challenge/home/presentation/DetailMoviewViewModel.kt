package mx.mariovaldez.code_challenge.home.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.code_challenge.home.domain.SearchVideoMovie
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI
import mx.mariovaldez.code_challenge.home.presentation.models.VideoResultUI

@HiltViewModel
internal class DetailMoviewViewModel @Inject constructor(
    private val searchVideoMovie: SearchVideoMovie,
) : ViewModel() {

    private lateinit var movie: MovieUI

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.DefaultState)
    val state: StateFlow<State> get() = _state

    fun getMovie(): MovieUI = movie

    fun searchVideoInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                searchVideoMovie(movie.id.toString())
            }
                .onSuccess { results ->
                    println(results)
                    _state.value = State.ShowPlayer(results[0])
                }
                .onFailure {
                    _state.value = State.ShowError
                }
        }
    }

    fun setMovie(movie: MovieUI) {
        this.movie = movie
    }

    sealed class State {

        data class ShowPlayer(val videoResultUI: VideoResultUI) : State()

        object ShowError : State()

        object DefaultState : State()
    }

    companion object {

        const val MOVIE_KEY = "movie"
    }
}