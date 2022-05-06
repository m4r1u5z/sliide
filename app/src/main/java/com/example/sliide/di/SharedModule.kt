package com.example.sliide.di

import com.example.sliide.data.UsersRepositoryImpl
import com.example.sliide.data.network.UsersMapper
import com.example.sliide.data.network.UsersNetworkStorage
import com.example.sliide.data.network.UsersService
import com.example.sliide.domain.repository.UsersRepository
import com.example.sliide.domain.usecase.UsersUseCase
import com.example.sliide.domain.usecase.impl.UsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gorest.co.in")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()

    @Provides
    fun provideUsersService(retrofit: Retrofit): UsersService =
        retrofit.create(UsersService::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()

    @Provides
    fun provideUsersMapper(): UsersMapper = UsersMapper()

    @Provides
    fun provideUsersUseCase(usersRepository: UsersRepository): UsersUseCase =
        UsersUseCaseImpl(usersRepository)

    @Provides
    fun provideUsersRepository(
        usersNetworkStorage: UsersNetworkStorage,
        usersMapper: UsersMapper,
    ): UsersRepository = UsersRepositoryImpl(usersNetworkStorage, usersMapper)
}
