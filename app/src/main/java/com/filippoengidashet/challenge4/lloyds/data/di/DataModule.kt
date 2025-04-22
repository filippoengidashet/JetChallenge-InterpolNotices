package com.filippoengidashet.challenge4.lloyds.data.di

import com.filippoengidashet.challenge4.lloyds.data.repository.CountryCodesRepositoryImpl
import com.filippoengidashet.challenge4.lloyds.data.repository.InterpolNoticesRepositoryImpl
import com.filippoengidashet.challenge4.lloyds.data.service.CountryCodesApiService
import com.filippoengidashet.challenge4.lloyds.data.service.InterpolNoticesApiService
import com.filippoengidashet.challenge4.lloyds.data.storage.UserDataStorage
import com.filippoengidashet.challenge4.lloyds.domain.model.StorageDao
import com.filippoengidashet.challenge4.lloyds.domain.repository.CountryCodesRepository
import com.filippoengidashet.challenge4.lloyds.domain.repository.InterpolNoticesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            )
            .build()
    }

    @Provides
    @Singleton
    @InterpolNoticesQualifier
    fun provideInterpolNoticesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ws-public.interpol.int/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideInterpolNoticesApiService(
        @InterpolNoticesQualifier retrofit: Retrofit
    ): InterpolNoticesApiService {
        return retrofit.create(InterpolNoticesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideInterpolNoticesRepository(
        service: InterpolNoticesApiService
    ): InterpolNoticesRepository {
        return InterpolNoticesRepositoryImpl(service)
    }

    @Provides
    @Singleton
    @CountryCodesQualifier
    fun provideCountryCodesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.interpol.int/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryCodesApiService(
        @CountryCodesQualifier retrofit: Retrofit
    ): CountryCodesApiService {
        return retrofit.create(CountryCodesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStorage(storage: UserDataStorage): StorageDao {
        return storage
    }

    @Provides
    @Singleton
    fun provideCountryCodesRepository(
        service: CountryCodesApiService, storage: StorageDao
    ): CountryCodesRepository {
        return CountryCodesRepositoryImpl(
            storageDao = storage,
            apiService = service,
        )
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class InterpolNoticesQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CountryCodesQualifier
}
