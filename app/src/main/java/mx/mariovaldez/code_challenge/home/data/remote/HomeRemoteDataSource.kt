package mx.mariovaldez.code_challenge.home.data.remote

import dagger.Reusable
import javax.inject.Inject
import mx.mariovaldez.code_challenge.data.remote.services.MovieServices
import mx.mariovaldez.code_challenge.home.data.remote.models.response.MovieResponse
import mx.mariovaldez.code_challenge.home.data.remote.models.response.VideoResponse

@Reusable
internal class HomeRemoteDataSource @Inject constructor(
    private val movieServices: MovieServices,
) {

    suspend fun getMovies(): MovieResponse = movieServices.getMostPopularMovies()

    suspend fun searchVideoMovie(idMovie: String): VideoResponse =
        movieServices.searchVideoMovie(idMovie)
}