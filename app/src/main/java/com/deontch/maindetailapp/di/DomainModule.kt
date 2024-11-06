package com.deontch.maindetailapp.di

import com.deontch.core.common.domain.utils.DispatcherProvider
import com.deontch.maindetailapp.domain.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    @Singleton
    abstract fun bindsPostExecutionThread(postExecutionThread: DispatcherProviderImpl): DispatcherProvider
}
