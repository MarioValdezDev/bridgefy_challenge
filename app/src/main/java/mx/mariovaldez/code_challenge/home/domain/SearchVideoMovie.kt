package mx.mariovaldez.code_challenge.home.domain

import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.withContext
import mx.mariovaldez.code_challenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.code_challenge.home.data.repository.HomeRepository
import mx.mariovaldez.code_challenge.home.presentation.models.VideoResultUI

@Reusable
internal class SearchVideoMovie @Inject constructor(
    private val dispatcherProvider: DefaultDispatcherProvider,
    private val homeRepository: HomeRepository,
) {

    suspend operator fun invoke(idMovie: String): List<VideoResultUI> =
        withContext(dispatcherProvider.default) {
            homeRepository.searchVideoMovie(idMovie)
        }
}
