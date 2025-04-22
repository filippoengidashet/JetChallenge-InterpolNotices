package com.filippoengidashet.challenge4.lloyds.application

import androidx.multidex.MultiDexApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        //Global Image disk caching
        Glide.with(applicationContext)
            .applyDefaultRequestOptions(
                RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            )
    }
}
