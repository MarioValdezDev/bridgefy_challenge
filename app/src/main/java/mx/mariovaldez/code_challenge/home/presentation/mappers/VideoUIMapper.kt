package mx.mariovaldez.code_challenge.home.presentation.mappers

import dagger.Reusable
import javax.inject.Inject
import mx.mariovaldez.code_challenge.domain.mapper.Mapper
import mx.mariovaldez.code_challenge.home.data.remote.models.response.VideoResult
import mx.mariovaldez.code_challenge.home.presentation.models.VideoResultUI

@Reusable
internal class VideoUIMapper @Inject constructor() : Mapper<VideoResult, VideoResultUI> {

    override fun map(value: VideoResult): VideoResultUI = with(value) {
        VideoResultUI(
            iso639_1,
            iso3166_1,
            name,
            key,
            site,
            size,
            type,
            official,
            publishedAt,
            id
        )
    }
}
