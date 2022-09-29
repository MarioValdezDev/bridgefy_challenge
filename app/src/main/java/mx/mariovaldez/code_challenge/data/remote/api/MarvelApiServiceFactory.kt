package mx.mariovaldez.code_challenge.data.remote.api

import javax.inject.Inject
import mx.mariovaldez.code_challenge.domain.remote.ServiceFactory
import retrofit2.Retrofit

internal class MarvelApiServiceFactory @Inject constructor(
    private val retrofit: Retrofit,
) : ServiceFactory {
    override fun <T> createApiService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}