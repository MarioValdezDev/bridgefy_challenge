package mx.mariovaldez.code_challenge.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.code_challenge.home.domain.GetMovies
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI

@HiltViewModel
internal class ListMoviesViewModel @Inject constructor(
    private val getMovies: GetMovies,
) : ViewModel() {

    private var allMovies: MutableList<MovieUI> = mutableListOf()

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun findMovies() {
        viewModelScope.launch {
            _state.value = State    .Loading
            kotlin.runCatching {
                getMovies()
            }
                .onSuccess { movies ->
                    println(movies)
                    allMovies.addAll(movies)
                    _state.value = State.ShowData(movies)
                }
                .onFailure {
                    _state.value = State.ShowError
                }
        }
    }

    fun showItemClicked(moviesPosition: Int) {
        viewModelScope.launch {
            _event.emit(Event.NavigateToMovieDetail(allMovies[moviesPosition]))
        }
    }

    sealed class State {

        object ShowError : State()

        object Loading : State()

        data class ShowData(val movies: List<MovieUI>) : State()
    }

    sealed class Event {

        data class NavigateToMovieDetail(val movieUI: MovieUI) : Event()
    }
}