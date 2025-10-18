package com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.core.di

import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.data.repository.AuthRepositoryImpl
import com.tss.nativeandroid_kotlin_clean_architecture_mvvm_xml.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
