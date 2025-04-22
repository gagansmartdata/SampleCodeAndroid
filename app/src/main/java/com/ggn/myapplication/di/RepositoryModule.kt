package com.ggn.myapplication.di

import com.ggn.myapplication.data.remote.ApiInterface
import com.ggn.myapplication.data.repository.UserRepositoryImpl
import com.ggn.myapplication.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class) //Can only be used in viewModels
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit
            .create(ApiInterface::class.java)
    }


    @Provides
    @ViewModelScoped
    fun provideLoginRepository(api: ApiInterface): UserRepository {
        return UserRepositoryImpl(api)
    }
}