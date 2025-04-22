package com.filippoengidashet.challenge4.lloyds.domain.di

import com.filippoengidashet.challenge4.lloyds.domain.repository.InterpolNoticesRepository
import com.filippoengidashet.challenge4.lloyds.domain.usecase.GetInterpolNoticesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideGetInterpolNoticesUseCase(
        repository: InterpolNoticesRepository
    ): GetInterpolNoticesUseCase {
        return GetInterpolNoticesUseCase(repository)
    }
}
