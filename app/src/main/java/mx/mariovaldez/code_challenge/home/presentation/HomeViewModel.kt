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
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI

@HiltViewModel
internal class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.IncompleteRequirements)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun onRequirementCompleted(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    sealed class State {

        object IncompleteRequirements : State()

        object CompleteRequirement : State()
    }

    sealed class Event {

        abstract val type: EventType

        data class NavigateToListMovies(
            override val type: EventType = EventType.NAVIGATION,
        ) : Event()

        data class NavigateToMovieDetail(
            val movie: MovieUI,
            override val type: EventType = EventType.NAVIGATION,
        ) : Event()
    }

    enum class EventType {

        NAVIGATION,
    }

}
