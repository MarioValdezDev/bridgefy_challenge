package mx.mariovaldez.code_challenge.home.presentation.mappers

import dagger.Reusable
import javax.inject.Inject
import mx.mariovaldez.code_challenge.domain.mapper.Mapper
import mx.mariovaldez.code_challenge.home.data.remote.models.response.Movie
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI

@Reusable
internal class MovieUIMapper @Inject constructor() : Mapper<Movie, MovieUI> {

    override fun map(value: Movie): MovieUI = with(value) {
        MovieUI(
            adult,
            backdropPath,
            genreIds,
            id,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            video,
            voteAverage,
            voteCount,
        )
    }
}