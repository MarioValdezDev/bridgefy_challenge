package mx.mariovaldez.code_challenge.home.data.repository

import dagger.Reusable
import javax.inject.Inject
import mx.mariovaldez.code_challenge.home.data.remote.HomeRemoteDataSource
import mx.mariovaldez.code_challenge.home.presentation.mappers.MovieUIMapper
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI

@Reusable
internal class HomeRepository @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val movieUIMapper: MovieUIMapper,
) {

    suspend fun getMovies(): List<MovieUI> =
        movieUIMapper.map(homeRemoteDataSource.getMovies().results)
}