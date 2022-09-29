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

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun findMovies() {
        viewModelScope.launch {
            kotlin.runCatching {
                getMovies()
            }
                .onSuccess { movies ->
                    println(movies)
                    _state.value = State.ShowData(movies)
                }
                .onFailure {
                    _state.value = State.ShowError
                }
        }
    }

    sealed class State {

        object ShowError : State()

        object Loading : State()

        data class ShowData(val movies: List<MovieUI>) : State()
    }

    sealed class Event {

        object NavigateToMovieDetail : Event()
    }
}