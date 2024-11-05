package com.deontch.maindetailapp.di

import com.deontch.core.network.auth.BuildConfiguration
import com.deontch.maindetailapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BuildConfigModule {

    @Singleton
    @Provides
    fun providesBuildConfiguration(): BuildConfiguration {
        return BuildConfiguration(
            debug = BuildConfig.DEBUG,
            appId = BuildConfig.APPLICATION_ID,
            buildType = BuildConfig.BUILD_TYPE,
            versionCode = BuildConfig.VERSION_CODE,
            versionName = BuildConfig.VERSION_NAME,
            baseUrl = BuildConfig.BASE_URL,
            apiKey = "" /** Add your API key here **/
        )
    }
}