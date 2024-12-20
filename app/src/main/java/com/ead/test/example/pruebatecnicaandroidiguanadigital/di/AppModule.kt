package com.ead.test.example.pruebatecnicaandroidiguanadigital.di

import com.ead.test.example.pruebatecnicaandroidiguanadigital.data.repository.DataItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideItemRepository(): DataItemRepository = DataItemRepository()
}