package mx.mariovaldez.code_challenge.home.data.remote

import dagger.Reusable
import javax.inject.Inject
import mx.mariovaldez.code_challenge.data.remote.services.MarvelServices
import mx.mariovaldez.code_challenge.home.data.remote.models.response.MovieResponse

@Reusable
internal class HomeRemoteDataSource @Inject constructor(
    private val marvelServices: MarvelServices,
) {

    suspend fun getMovies(): MovieResponse = marvelServices.getMostPopularMovies()
}