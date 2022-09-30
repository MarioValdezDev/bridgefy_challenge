package mx.mariovaldez.code_challenge.data.remote.services

import mx.mariovaldez.code_challenge.BuildConfig
import mx.mariovaldez.code_challenge.home.data.remote.models.response.MovieResponse
import mx.mariovaldez.code_challenge.home.data.remote.models.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieServices {

    @GET("movie/{idMovie}/videos")
    suspend fun searchVideoMovie(
        @Path("idMovie") idMovie: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") lang: String = "en-US",
    ): VideoResponse

    @GET("movie/popular")
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") lang: String = "en-US",
        @Query("page") page: String = "1",
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") lang: String = "en-US",
        @Query("page") page: String = "1",
    )

    @GET("genre/movie/list")
    suspend fun getMovieGenders(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") lang: String = "en-US",
    )

}