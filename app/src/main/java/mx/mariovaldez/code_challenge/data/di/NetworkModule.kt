package mx.mariovaldez.code_challenge.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import mx.mariovaldez.code_challenge.BuildConfig
import mx.mariovaldez.code_challenge.data.remote.api.MarvelApiServiceFactory
import mx.mariovaldez.code_challenge.data.remote.services.MarvelServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val READ_TIMEOUT: Long = 120
    private const val CONNECT_TIMEOUT: Long = 120

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .disableHtmlEscaping()
        .create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder().apply {
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(
                HttpLoggingInterceptor { message ->
                    Timber.i(message)
                }.apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideApiServices(
        marvelApiServiceFactory: MarvelApiServiceFactory,
    ): MarvelServices = marvelApiServiceFactory.createApiService(
        MarvelServices::class.java
    )
}
