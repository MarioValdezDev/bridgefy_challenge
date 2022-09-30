package mx.mariovaldez.code_challenge.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import mx.mariovaldez.code_challenge.domain.dispatchers.DefaultDispatcherProvider
import mx.mariovaldez.code_challenge.domain.dispatchers.DispatcherProvider

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DispatchersModule {

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(
        defaultDispatcherProvider: DefaultDispatcherProvider,
    ): DispatcherProvider
}
