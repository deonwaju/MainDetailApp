package com.deontch.maindetailapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainDetailApp: Application(), ImageLoaderFactory {
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    override fun newImageLoader(): ImageLoader {
        return imageLoader
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
